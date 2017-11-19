package com.psp.util;


import java.util.Random;

public class NumUtil {
	/**
	 * 获取随机的验证码
	 */
	public static String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	private static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	/**
	 * 处理字符串至long型
	 */
	public static long toLong(String value, long def) {
		if (value == null)
			return def;
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return def;
		}
	}

	/**
	 * 处理字符串至int型
	 */
	public static int toInt(String value, int def) {
		if (value == null)
			return def;
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return def;
		}
	}

	public static int toInt(Object value, int def) {
		return toInt(String.valueOf(value), def);
	}

	public static float toFloat(String value, float def) {
		if (value == null)
			return def;
		try {
			return (float) Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return def;
		}
	}

	/**
	 * 返回浮点
	 * 
	 * @param value
	 * @param def
	 * @return
	 */
	public static float toFloat(Object value, float def) {
		return toFloat(String.valueOf(value), def);
	}

	public static Double toDouble(String value, double def) {
		if (value == null)
			return def;
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return def;
		}
	}
}
