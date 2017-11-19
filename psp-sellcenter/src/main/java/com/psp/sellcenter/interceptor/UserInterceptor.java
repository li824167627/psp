package com.psp.sellcenter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(getClass());
//	@Autowired
//	UserService userServiceImpl;
//	@Autowired
//	UserCacheDao userCacheImpl;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		request.setCharacterEncoding("utf-8");
//		boolean flag = false;
//		String token = request.getHeader("token");
//		if (token != null) {
//			this.logger.info("token is:" + token);
//			UserBean user = this.userServiceImpl.existUser(token);
//			if (user != null) {
//				request.setAttribute("userId", user.getUid());
//				request.setAttribute("token", token);
//				request.setAttribute("user", user);
//				flag = true;
//				this.logger.info("userid is :" + user.getUid());
//			}
//		} else {
//			UserBean user = this.userServiceImpl.getUser("9a4609a38cb84d62a5fcde2c83c8e98c");
//			if (user != null) {
//				request.setAttribute("userId", user.getUid());
//				request.setAttribute("token", token);
//				request.setAttribute("user", user);
//				flag = true;
//				this.logger.info("userid is :" + user.getUid());
//			}
//		}
//		if (!flag) {
//			BaseResult result = new BaseResult();
//
//			Rescode rescode = RescodeConstants.getInstance().get("token_fail");
//			result.setFlag(false);
//			result.setRescode(rescode.getCode());
//			result.setMsg(rescode.getMsg());
//			response.setCharacterEncoding("utf-8");
//			response.getWriter().write(JSON.toJSONString(result));
//			return false;
//		}
		return super.preHandle(request, response, handler);
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
