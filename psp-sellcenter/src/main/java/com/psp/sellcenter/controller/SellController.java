package com.psp.sellcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.sellcenter.cache.dao.SellerCacheDao;
import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ObjectResult;
import com.psp.sellcenter.controller.res.bean.RSellerBean;
import com.psp.sellcenter.controller.springmvc.req.ConfirmFindPwdCodeParam;
import com.psp.sellcenter.controller.springmvc.req.GetSellerParam;
import com.psp.sellcenter.controller.springmvc.req.LoginParam;
import com.psp.sellcenter.controller.springmvc.req.ResetPwdParam;
import com.psp.sellcenter.controller.springmvc.req.SendFindPwdCodeParam;
import com.psp.sellcenter.controller.springmvc.req.SendVCodeParam;
import com.psp.sellcenter.controller.springmvc.req.UpdateNameParam;
import com.psp.sellcenter.model.Code;
import com.psp.sellcenter.service.SellerService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.util.AppTextUtil;

@Component
public class SellController {
	Logger logger = Logger.getLogger(SellController.class);
	
	@Autowired
	SellerService sellerServiceImpl;
	
	@Autowired
	SellerCacheDao sellerCacheImpl;

	public ObjectResult<RSellerBean> login(LoginParam param, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> result = new ObjectResult<RSellerBean>();

		String phone = param.getPhoneNum();
		String pwd = param.getPassword();
		String vcode = param.getImgCode();
		String device = param.getDevice();
		try {
			String sessionId = request.getSession().getId();
			String ip = AppTextUtil.getIpAddr(request);
			RSellerBean user = sellerServiceImpl.login(sessionId, phone, pwd, vcode, device, ip);
			logger.info("login user is:" + user);
			if (user != null) {
				result.setToken(sessionId);
				result.setData(user);
				result.setFlag(true);
			} else {
				Code code = sellerCacheImpl.getLoginCode(phone);
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

	public ObjectResult<RSellerBean> updateName(UpdateNameParam param, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> result = new ObjectResult<RSellerBean>();
		try {

			String uid = (String)request.getAttribute("sellerId");
			String name = param.getName();
			RSellerBean user = sellerServiceImpl.updateName(uid, name);
			logger.info("login user is:" + user);
			if (user != null) {
				result.setData(user);
				result.setFlag(true);
			} 
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}

	public ObjectResult<RSellerBean> getSeller(GetSellerParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult sendVCode(SendVCodeParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RSellerBean> sendFindPwdCode(SendFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RSellerBean> confirmFindPwdCode(ConfirmFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult resetPwd(ResetPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String uid = (String)request.getAttribute("sellerId");
			String pwd = param.getOldPwd();
			String newPwd = param.getPassword();
			String subPwd = param.getConfirmPwd();
			boolean flag = sellerServiceImpl.resetPwd(uid, pwd, newPwd, subPwd);
			if (flag) {
				result.setFlag(true);
			}
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}

}
