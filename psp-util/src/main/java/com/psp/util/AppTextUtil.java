package com.psp.util;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


/**
 * App工具
 * 
 * @author candy-it
 *
 */
public class AppTextUtil {

	/**
	 * 处理特殊字符
	 * 
	 * @param codePoint
	 * @return
	 */
	public static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 处理微信的名称
	 * 
	 * @param name
	 * @return
	 */
	public static String doWechatName(String name) {
		if (name != null)
			name = name.replaceAll("[^\u4e00-\u9fa5\\w\\s]+", "");
		return name;
	}

	/**
	 * 获取数据库关键字，Guuid
	 * 
	 * @return
	 */
	public static String getPrimaryKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public static String getToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		} //
		return ip;
	}

	public static void main(String[] args) {
			System.out.println(getPrimaryKey());
	}
	
	/**
	 * 获取文件key
	 */
	public static String getFileKey() {
		return DateUtil.getDate("yyyyMMdd") + System.currentTimeMillis() + RandomUtil.getRandomCharAndNum(12);
	}
	
	
}
