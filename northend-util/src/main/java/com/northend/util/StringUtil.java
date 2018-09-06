package com.northend.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean isEmail(String email) {
		String str = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&\'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean pwdIsAllowed(String pwd) {
		String str = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(pwd);
		return m.matches();
	}

	public static boolean isIdNo(String idNo) {
		String str = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(idNo);
		return m.matches();
	}

	public static void main(String[] args) {
		System.out.println(isIdNo("130434199106247331s"));
	}

	public static String arrayToStr(String[] array) {
		String str = null;
		StringBuffer sb = new StringBuffer();
		if (array != null && array.length > 0) {
			sb.append("[");
			for (int i = 0; i < array.length; i++) {
				sb.append("'" + array[i] + "',");
			}
			str = sb.substring(0, sb.length() - 1) + "]";
		}
		return str;
	}

	/**
	 * 格式化字符串
	 * 
	 * @param name
	 * @return
	 */
	public static String format(String value) {
		if (value == null)
			return "";
		if (value.trim().length() == 0)
			return "";
		return value;
	}

	/**
	 * stream to string
	 * 
	 * @param is
	 * @return
	 */
	public static String streamToString(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		try {
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toString();
	}

}
