package com.psp.provider.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.constants.Rescode;
import com.psp.provider.constants.RescodeConstants;
import com.psp.provider.controller.res.BaseResult;
import com.psp.provider.model.AccountBean;
import com.psp.provider.service.AccountService;
import com.psp.util.StringUtil;

public class UserInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	AccountService accountServiceImpl;

	@Autowired
	AccountCacheDao accountCacheImpl;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		boolean flag = false;
		String token = request.getHeader("token");
		if (!StringUtil.isEmpty(token)) {
			logger.info("token is:" + token);
			AccountBean account = accountServiceImpl.getAccountByToken(token);
			if (account != null) {
				request.setAttribute("accountId", account.getAid());
				request.setAttribute("token", token);
				request.setAttribute("account", account);
				flag = true;
				logger.info("accountid is :" + account.getAid());
			}
//		} else {
//			AccountBean account = accountServiceImpl.getAccountById("437962beb13e48d9bb057fa4ff893720");
//			if (account != null) {
//				request.setAttribute("accountId", account.getAid());
//				request.setAttribute("token", token);
//				request.setAttribute("account", account);
//				flag = true;
//				logger.info("accountid is :" + account.getAid());
//			}
//		}
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
