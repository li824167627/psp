package com.psp.sellcenter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.psp.sellcenter.cache.dao.SellerCacheDao;
import com.psp.sellcenter.constants.Rescode;
import com.psp.sellcenter.constants.RescodeConstants;
import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.service.SellerService;

public class UserInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	SellerService sellerServiceImpl;

	@Autowired
	SellerCacheDao sellerCacheImpl;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		boolean flag = false;
		String token = request.getHeader("token");
		if (token != null) {
			logger.info("token is:" + token);
			SellerBean seller = sellerServiceImpl.getSellerByToken(token);
			if (seller != null) {
				request.setAttribute("sellerId", seller.getSid());
				request.setAttribute("token", token);
				request.setAttribute("seller", seller);
				flag = true;
				logger.info("sellerid is :" + seller.getSid());
			}
		} else {
			SellerBean seller = sellerServiceImpl.getSellerById("1862a08ec8a94e0ab1c47f91503396a5");
			if (seller != null) {
				request.setAttribute("sellerId", seller.getSid());
				request.setAttribute("token", token);
				request.setAttribute("seller", seller);
				flag = true;
				logger.info("sellerid is :" + seller.getSid());
			}
			
		}
		if (!flag) {
			BaseResult result = new BaseResult();
			Rescode rescode = RescodeConstants.getInstance().get("token_fail");
			result.setFlag(false);
			result.setRescode(rescode.getCode());
			result.setMsg(rescode.getMsg());
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JSON.toJSONString(result));
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
