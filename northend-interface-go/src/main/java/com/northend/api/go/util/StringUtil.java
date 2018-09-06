package com.northend.api.go.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(String value) {
		if (value == null)
			return true;
		if (value.trim().length() == 0)
			return true;
		return false;
	}

	public static boolean isClassName(String value) {
		if (!isEmpty(value)) {
			Pattern pattern = Pattern.compile("^[A-Za-z_$]+[A-Za-z_$\\d]+$");
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}
		return false;
	}

	/**
	 * 首字母大写
	 * 
	 * @param name
	 * @return
	 */
	public static String toFirstUpperCase(String name) {
		// 首字母大写
		if (null == name || "".equals(name)) {
			return null;
		}
		return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
	}

	/**
	 * 首字母小写
	 * 
	 * @param name
	 * @return
	 */
	public static String toFirstLowerCase(String name) {
		// 首字母大写
		if (null == name || "".equals(name)) {
			return null;
		}
		return name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
	}
}
