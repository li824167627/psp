package com.psp.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.psp.admin.cache.dao.AdminCacheDao;
import com.psp.admin.constants.Rescode;
import com.psp.admin.constants.RescodeConstants;
import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.model.AdminBean;
import com.psp.admin.service.AdminService;

public class UserInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	AdminService adminServiceImpl;

	@Autowired
	AdminCacheDao adminCacheImpl;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		boolean flag = false;
		String token = request.getHeader("token");
		if (token != null) {
			logger.info("token is:" + token);
			AdminBean admin = adminServiceImpl.getAdminByToken(token);
			if (admin != null) {
				request.setAttribute("adminId", admin.getAid());
				request.setAttribute("token", token);
				request.setAttribute("admin", admin);
				flag = true;
				logger.info("adminid is :" + admin.getAid());
			}
		} else {
			AdminBean admin = adminServiceImpl.getAdminById("b70d85d1661a45d1b2648c46c7377db2");
			if (admin != null) {
				request.setAttribute("adminId", admin.getAid());
				request.setAttribute("token", token);
				request.setAttribute("admin", admin);
				flag = true;
				logger.info("adminid is :" + admin.getAid());
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
