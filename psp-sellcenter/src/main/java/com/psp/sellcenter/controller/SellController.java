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
import com.psp.sellcenter.controller.springmvc.req.GetSellerParam;
import com.psp.sellcenter.controller.springmvc.req.LoginParam;
import com.psp.sellcenter.controller.springmvc.req.UpdateNameParam;
import com.psp.sellcenter.controller.springmvc.req.UpdatePasswordParam;
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

		String vcode = param.getImgCode();
		String device = param.getDevice();
		try {
			String sessionId = request.getSession().getId();
			String ip = AppTextUtil.getIpAddr(request);
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}

	public BaseResult updateName(UpdateNameParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult updatePassWord(UpdatePasswordParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RSellerBean> getSeller(GetSellerParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
