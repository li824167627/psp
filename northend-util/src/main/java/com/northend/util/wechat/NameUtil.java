package com.northend.util.wechat;

/**
 * 微信名字过滤
 * 
 * @author cuihaidong
 *
 */
public class NameUtil {
	/**
	 * 过滤名字中的表情
	 * 
	 * @param name
	 * @return
	 */
	public static String filter(String name) {
		if (name != null)
			name = name.replaceAll("[^\u4e00-\u9fa5\\w\\s]+", "");
		return name;
	}
}
