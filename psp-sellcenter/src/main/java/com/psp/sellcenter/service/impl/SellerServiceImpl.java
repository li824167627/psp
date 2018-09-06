package com.psp.sellcenter.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psp.sellcenter.cache.dao.SellerCacheDao;
import com.psp.sellcenter.controller.res.bean.RSellerBean;
import com.psp.sellcenter.model.Code;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.persist.dao.SellerDao;
import com.psp.sellcenter.service.SellerService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.util.MD5Util;
import com.psp.util.NumUtil;
import com.psp.util.StringUtil;

@Service
public class SellerServiceImpl implements SellerService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	SellerDao sellerImpl;

	@Autowired
	SellerCacheDao sellerCacheImpl;

	@Override
	public SellerBean getSellerByToken(String token) {
		if (token == null) {
			return sellerImpl.selectOneById("ec63d61665db4fd59836c1a4c7fe370c");
		}
		String sid = sellerCacheImpl.getSellerIdByToken(token);
		if (sid == null) {
			return null;
		}
		SellerBean user = sellerImpl.selectOneById(sid);
		if (user.getStatus() != 0) {
			throw new ServiceException("account_is_forzen");
		}
		return user;
	}

	@Override
	public SellerBean getSellerById(String sid) {
		SellerBean user = sellerImpl.selectOneById(sid);
		if (user.getStatus() != 0) {
			throw new ServiceException("account_is_forzen");
		}
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RSellerBean login(String sessionId, String phone, String pwd, String vcode, String device, String ip) {
		SellerBean user = sellerImpl.selectOneByPhone(phone);
		if (user == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		// Code code = sellerCacheImpl.getLoginCode(phone);
		// if (code == null) {
		// code = new Code();
		// code.setNum(0);
		// sellerCacheImpl.setLoginCode(phone, code);
		// } else if (code.getNum() > 4) {
		// if (StringUtil.isEmpty(code.getCode())) {
		// throw new ServiceException("imgcode_is_cross");
		// }
		// if (!code.getCode().equals(vcode.toUpperCase())) {
		// throw new ServiceException("imgcode_is_error");
		// }
		// }

		pwd = MD5Util.md5(pwd);
		if (!pwd.equals(user.getPassword())) {
			// code.setNum(code.getNum() + 1);
			// sellerCacheImpl.setLoginCode(phone, code);
			throw new ServiceException("user_password_is_error");
		}

		if (NumUtil.toInt(user.getStatus(), 0) == 1) {
			throw new ServiceException("account_is_forzen");
		}

		sellerCacheImpl.setAccountIdTOKEN(sessionId, user.getSid(), 24 * 1000 * 60L);
		boolean flag = sellerImpl.updateLoginTime(user.getSid()) > 0;
		if (!flag) {
			throw new ServiceException("user_update_error");
		}
		// if (flag) {
		// logger.info("sessionId= " + sessionId);
		// UserLoginLogBean loginLog = new UserLoginLogBean();
		// loginLog.setDevice(device);
		// loginLog.setLoginIp(ip);
		// loginLog.setUid(user.getAid());
		// loginLog.setOstate(1);// 1 登录 2注册
		// flag = adminImpl.insertLoginLog(loginLog) > 0;
		// if (!flag) {
		// throw new ServiceException("user_log_insert_error");
		// }
		// }

		return parse(user);
	}

	private RSellerBean parse(SellerBean user) {
		RSellerBean seller = new RSellerBean();
		seller.setSid(user.getSid());
		seller.setPhoneNum(user.getPhoneNum());
		seller.setStatus(user.getStatus());
		seller.setUsername(user.getUsername());
		seller.setLetter(StringUtil.getFirstLetter(user.getUsername()));
		return seller;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean resetPwd(String sid, String pwd, String newPwd, String subPwd) {
		SellerBean user = sellerImpl.selectOneById(sid);
		if (user == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		if (!MD5Util.md5(pwd).equals(user.getPassword())) {
			throw new ServiceException("user_password_is_error");
		}

		if (newPwd != null && !newPwd.equals(subPwd)) {
			throw new ServiceException("user_password_is_not_same");
		}

		user.setPassword(MD5Util.md5(newPwd));
		boolean flag = sellerImpl.updatePwd(user) > 0;
		if (!flag) {
			throw new ServiceException("update_seller_error");
		}
		return flag;
	}

	@Override
	public RSellerBean updateName(String uid, String name) {
		SellerBean user = sellerImpl.selectOneById(uid);
		if (user == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		user.setUsername(name);
		boolean flag = sellerImpl.updateName(user) > 0;
		if (!flag) {
			throw new ServiceException("update_seller_error");
		}
		return parse(user);
	}

}
