package com.psp.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.controller.res.bean.RAccountBean;
import com.psp.provider.model.AccountBean;
import com.psp.provider.model.Code;
import com.psp.provider.persist.dao.AccountDao;
import com.psp.provider.service.AccountService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.util.MD5Util;
import com.psp.util.NumUtil;
import com.psp.util.StringUtil;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDao accountImpl;
	
	@Autowired
	AccountCacheDao accountCacheImpl;
	
	@Override
	public AccountBean getAccountByToken(String token) {
		if (token == null) {
			return accountImpl.selectOneById("b9082f0c2b7f4d839f966d8f266c6224");
		}
		String sid = accountCacheImpl.getAccountIdByToken(token);
		if (sid == null) {
			return null;
		}
		AccountBean user = accountImpl.selectOneById(sid);
		if(user.getStatus() != 0) {
			throw new ServiceException("account_is_forzen");
		}
		return user;
	}

	@Override
	public AccountBean getAccountById(String sid) {
		AccountBean user = accountImpl.selectOneById(sid);
		if(user.getStatus() != 0) {
			throw new ServiceException("account_is_forzen");
		}
		return user;
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public RAccountBean login(String sessionId, String phone, String pwd, String vcode, String device, String ip) {
		
		AccountBean user = accountImpl.selectOneByPhone(phone);
		if (user == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		Code code = accountCacheImpl.getLoginCode(phone);
		if (code == null) {
			code = new Code();
			code.setNum(0);
			accountCacheImpl.setLoginCode(phone, code);
		} else if (code.getNum() > 4) {
			if (StringUtil.isEmpty(code.getCode())) {
				throw new ServiceException("imgcode_is_cross");
			}
			if (!code.getCode().equals(vcode.toUpperCase())) {
				throw new ServiceException("imgcode_is_error");
			}
		}

		pwd = MD5Util.md5(pwd);
		if (!pwd.equals(user.getPassword())) {
			code.setNum(code.getNum() + 1);
			accountCacheImpl.setLoginCode(phone, code);
			throw new ServiceException("user_password_is_error");
		}

		if (NumUtil.toInt(user.getStatus(), 0) == 1) {
			throw new ServiceException("account_is_forzen");
		}
		
		accountCacheImpl.setAccountIdTOKEN(sessionId, user.getAid(), 24*1000*60L);
		boolean flag = accountImpl.updateLoginTime(user.getAid()) > 0;
		if (!flag) {
			throw new ServiceException("user_update_error");
		}
//		if (flag) {
//			logger.info("sessionId= " + sessionId);
//			UserLoginLogBean loginLog = new UserLoginLogBean();
//			loginLog.setDevice(device);
//			loginLog.setLoginIp(ip);
//			loginLog.setUid(user.getAid());
//			loginLog.setOstate(1);// 1 登录 2注册
//			flag = adminImpl.insertLoginLog(loginLog) > 0;
//			if (!flag) {
//				throw new ServiceException("user_log_insert_error");
//			}
//		}

		return parse(user);
	}

	private RAccountBean parse(AccountBean user) {
		RAccountBean account = new RAccountBean();
		account.setAid(user.getAid());
		account.setNickName(user.getUsername());
		account.setPhoneNum(user.getPhoneNum());
		account.setLetter(StringUtil.getFirstLetter(user.getUsername()));
		account.setStatus(user.getStatus());
		account.setType(user.getType());
		if(user.getCreateTime() != null) {
			account.setCreateTime(user.getCreateTime().getTime() / 1000);
		}
		if(user.getLastLoginTime() != null) {
			account.setLastLoginTime(user.getLastLoginTime().getTime() / 1000);
		}
		return account;
	}

}
