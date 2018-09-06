package com.northend.util;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	 * 获取文件key
	 */
	public static String getFileKey() {
		return DateUtil.getDate("yyyyMMdd") + System.currentTimeMillis() + RandomUtil.getRandomCharAndNum(12);
	}

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
	
	public static void main(String[] args) {
		for(int i=0;i<248;i++) {

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
			
			System.out.println(format.format(randomDate("2016-08-01","2017-07-31")));
		}
		/*for(int i=0;i<248;i++) {
			System.out.println(RandomUtil.getRandomCharAndNum(8, 1));
		}*/
		
		
	}
	
	private static Date randomDate(String beginDate,String  endDate ){ 

		try { 

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 

		Date start = format.parse(beginDate);//构造开始日期 

		Date end = format.parse(endDate);//构造结束日期 

		//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。 

		if(start.getTime() >= end.getTime()){ 

		return null; 

		} 

		long date = random(start.getTime(),end.getTime()); 

		return new Date(date); 

		} catch (Exception e) { 

		e.printStackTrace(); 

		} 

		return null; 

		}  
	
	private static long random(long begin,long end){ 

		long rtn = begin + (long)(Math.random() * (end - begin)); 

		//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值 

		if(rtn == begin || rtn == end){ 

		return random(begin,end); 

		} 

		return rtn; 

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
	 * 订单编号
	 * 
	 * @return
	 */
	public static String getOrderNo(String pre) {
		String format = "yyyyMMddHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		StringBuffer sb = new StringBuffer(pre);
		sb.append(sdf.format(System.currentTimeMillis()));
		sb.append(RandomUtil.getRandomCharAndNum(8, 1));
		return sb.toString();
	}

	/**
	 * 处理电话
	 * 
	 * @param phone
	 * @return
	 */
	public static String filterPhone(String phone) {
		return phone.substring(0, 3) + "****" + phone.substring(7);
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

}
