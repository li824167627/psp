package com.psp.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.controller.res.bean.RAccountBean;
import com.psp.provider.model.AccountBean;
import com.psp.provider.model.Code;
import com.psp.provider.persist.dao.AccountDao;
import com.psp.provider.service.AccountService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.provider.service.impl.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.MD5Util;
import com.psp.util.NumUtil;
import com.psp.util.StringUtil;

@Service
public class AccountServiceImpl implements AccountService {
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AccountDao accountImpl;
	
	@Autowired
	AccountCacheDao accountCacheImpl;
	
	@Override
	public AccountBean getAccountByToken(String token) {
		if (token == null) {
			return accountImpl.selectOneById("437962beb13e48d9bb057fa4ff893720");
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
//		Code code = accountCacheImpl.getLoginCode(phone);
//		if (code == null) {
//			code = new Code();
//			code.setNum(0);
//			accountCacheImpl.setLoginCode(phone, code);
//		} else if (code.getNum() > 4) {
//			if (StringUtil.isEmpty(code.getCode())) {
//				throw new ServiceException("imgcode_is_cross");
//			}
//			if (!code.getCode().equals(vcode.toUpperCase())) {
//				throw new ServiceException("imgcode_is_error");
//			}
//		}

		pwd = MD5Util.md5(pwd);
		if (!pwd.equals(user.getPassword())) {
//			code.setNum(code.getNum() + 1);
//			accountCacheImpl.setLoginCode(phone, code);
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean resetPwd(AccountBean account, String pwd, String newPwd, String subPwd) {
		if (account == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		if (!MD5Util.md5(pwd).equals(account.getPassword())) {
			throw new ServiceException("user_password_is_error");
		}
		
		if (newPwd != null && !newPwd.equals(subPwd)) {
			throw new ServiceException("user_password_is_not_same");
		}
		
		account.setPassword(MD5Util.md5(newPwd));
		boolean flag = accountImpl.updatePwd(account) > 0;
		if(!flag) {
			throw new ServiceException("user_update_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RAccountBean updateName(AccountBean account, String name) {
		if (account == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		account.setUsername(name);
		boolean flag = accountImpl.updateName(account) > 0;
		if(!flag) {
			throw new ServiceException("update_seller_error");
		}
		return parse(account);
	}

	@Override
	public PageResult<RAccountBean> getAccountList(AccountBean account, int page, int pageSize) {
		PageResult<RAccountBean> result = new PageResult<RAccountBean>();
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		int count = accountImpl.selectAccountCount(account.getPid());
		if(count == 0) {
			return null;
		}
		List<AccountBean> resList = accountImpl.selectAccounts(page, pageSize, account.getPid());
		List<RAccountBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (AccountBean bean : resList) {
				RAccountBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}

	@Override
	public boolean resetAccountPwd(AccountBean account, String aid) {
		boolean flag = false;
		AccountBean oa = accountImpl.selectOneById(aid);
		if(oa == null) {
			throw new ServiceException("object_is_not_exist", "服务商账号");
		}
		oa.setPassword(MD5Util.md5("000000"));
		flag = accountImpl.updateAccount(oa) > 0;
		if(!flag) {
			throw new ServiceException("update_provider_account_error");
		}
		return flag;
	}

	@Override
	public boolean addAccount(AccountBean account, String name, String phone, String password) {
		boolean flag = false;
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		AccountBean newaccount = accountImpl.selectOneByPhone(phone);
		if(newaccount != null) {
			throw new ServiceException("object_is_exist", "当前手机账号");
		}
		newaccount = new AccountBean();
		newaccount.setPid(account.getPid());
		newaccount.setAid(AppTextUtil.getPrimaryKey());
		newaccount.setUsername(name);
		newaccount.setPhoneNum(phone);
		newaccount.setType(0);//0 员工 1 服务商管理员
		newaccount.setStatus(0);// 0 正常
		newaccount.setPassword(MD5Util.md5(password));
		flag = accountImpl.insertAccount(newaccount) > 0;
		if(!flag) {
			throw new ServiceException("add_provider_account_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delAccount(AccountBean account, String aid) {
		boolean flag = false;
		AccountBean oa = accountImpl.selectOneById(aid);
		if(oa == null) {
			throw new ServiceException("object_is_not_exist", "服务商账号");
		}
		//account.setPassword(MD5Util.md5("000000"));
		oa.setStatus(1);// 禁用
		flag = accountImpl.updateAccount(oa) > 0;
		if(!flag) {
			throw new ServiceException("update_provider_account_error");
		}
		return flag;
	}

}
