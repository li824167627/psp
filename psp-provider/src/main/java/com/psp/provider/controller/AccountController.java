package com.psp.provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.controller.res.BaseResult;
import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.RAccountBean;
import com.psp.provider.controller.springmvc.req.ConfirmFindPwdCodeParam;
import com.psp.provider.controller.springmvc.req.GetAccountParam;
import com.psp.provider.controller.springmvc.req.LoginParam;
import com.psp.provider.controller.springmvc.req.ResetPwdParam;
import com.psp.provider.controller.springmvc.req.SendFindPwdCodeParam;
import com.psp.provider.controller.springmvc.req.SendVCodeParam;
import com.psp.provider.model.Code;
import com.psp.provider.service.AccountService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.util.AppTextUtil;

@Component
public class AccountController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AccountService accountServiceImpl;
	
	@Autowired
	AccountCacheDao accountCacheImpl;

	public ObjectResult<RAccountBean> login(LoginParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RAccountBean> result = new ObjectResult<RAccountBean>();

		String phone = param.getPhoneNum();
		String pwd = param.getPassword();
		String vcode = param.getImgCode();
		String device = param.getDevice();
		try {
			String sessionId = request.getSession().getId();
			String ip = AppTextUtil.getIpAddr(request);
			RAccountBean user = accountServiceImpl.login(sessionId, phone, pwd, vcode, device, ip);
			logger.info("login user is:" + user);
			if (user != null) {
				result.setToken(sessionId);
				result.setData(user);
				result.setFlag(true);
			} else {
				Code code = accountCacheImpl.getLoginCode(phone);
				if (code != null) {
					result.setErrorCount(code.getNum());
				}
			}
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}

	public BaseResult sendVCode(SendVCodeParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAccountBean> sendFindPwdCode(SendFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAccountBean> confirmFindPwdCode(ConfirmFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult resetPwd(ResetPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAccountBean> getAccount(GetAccountParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
