package com.psp.sellcenter.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.psp.sellcenter.model.DeviceBean;
import com.psp.util.StringUtil;

/**
 * 公共拦截器
 * 
 * @author cuihaidong
 *
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(this.getClass());
	private long startTime;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("start----------------------------------------------");
		startTime = System.currentTimeMillis();

		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*"); // https://businesscard.yuandibaozha.com,多个值用逗号隔开
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "30");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed", "1");

		// 浏览器信息
		String appName = request.getHeader("appName");// 浏览器名称
		String appVersion = request.getHeader("appVersion");// 浏览器版本
		String appCodeName = request.getHeader("appCodeName");// 浏览器代码名
		String platform = request.getHeader("platform");// 浏览器操作系统

		// 设备信息
		DeviceBean device = new DeviceBean();
		device.setAppName(appName == null ? "xx浏览器名称" : appName);
		device.setAppVersion(appVersion == null ? "xx版本" : appVersion);
		device.setAppCodeName(appCodeName == null ? "xx代码名" : appCodeName);
		device.setPlatform(platform == null ? "xx操作系统" : platform);

		request.setAttribute("device", device);

		// 输出请求头参数
		logger.info("请求Req-Device info：" + JSON.toJSONString(device));

		logger.info("请求Url：" + request.getServletPath());
		String url = request.getRequestURL().toString();
		logger.info("请求Req-Url：" + url);

		// 输出请求头部参数
		StringBuffer sb = new StringBuffer();
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			String[] vs = map.get(key);
			String value = "";
			for (String s : vs) {
				value += s;
			}
			sb.append("&" + key + "=" + value);
		}

		// 输出请求参数
		sb = new StringBuffer();
		map = request.getParameterMap();
		for (String key : map.keySet()) {
			String[] vs = map.get(key);
			String value = "";
			for (String s : vs) {
				value += s;
			}
			sb.append("&" + key + "=" + value);
		}
		String getUrl = sb.length() == 0 ? url : url + "?" + sb.toString().substring(1);
		logger.info(getUrl);

		String requestStr = StringUtil.streamToString(request.getInputStream());
		logger.info("请求Req-String：" + requestStr);

		response.setCharacterEncoding("utf-8");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
		logger.info("end----------------------------------------------");
		super.afterCompletion(request, response, handler, ex);
	}
}