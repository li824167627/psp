package com.psp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringUtil {

	public static boolean isEmpty(String value) {
		if (value == null)
			return true;
		if (value.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 获取首字母
	 * 
	 * @param value
	 * @return
	 */
	public static String getFirstLetter(String value) {
		if (value == null || value.trim().length() == 0) {
			return "空";
		} else {
			return value.trim().substring(0, 1).toUpperCase();
		}
	}

	/***
	 * 合并字节数组
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] mergeArray(byte[]... a) {
		// 合并完之后数组的总长度
		int index = 0;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i].length;
		}
		byte[] result = new byte[sum];
		for (int i = 0; i < a.length; i++) {
			int lengthOne = a[i].length;
			if (lengthOne == 0) {
				continue;
			}
			// 拷贝数组
			System.arraycopy(a[i], 0, result, index, lengthOne);
			index = index + lengthOne;
		}
		return result;
	}

	/**
	 * 两个数组是否有相同字段
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	public static boolean equals(String[] parent, String[] child) {
		for (String p : parent) {
			for (String c : child) {
				if (c.equals(p))
					return true;
			}
		}

		return false;
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

	/**
	 * 截取字符串
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substring(String source, int len) {
		String result = source;
		int length = source.codePointCount(0, source.length() - 1);
		try {
			if (length > len) {
				result = source.substring(source.offsetByCodePoints(0, 0), source.offsetByCodePoints(0, len));
			}
		} catch (Exception e) {
		}
		return result;
	}
}
