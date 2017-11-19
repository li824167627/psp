package com.psp.util;


import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 中文的拼音工具类
 * 
 * @author cuihaidong
 *
 */
public class PinYinUtil {
	/**
	 * 获取字符串的第一个字符的首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getHeadFirstChar(String str) {
		if (str != null && !str.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			char word = str.charAt(0);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				return sb.append(pinyinArray[0].charAt(0)).toString().toUpperCase();
			} else {
				return sb.append(word).toString().toUpperCase();
			}
		}
		return null;
	}

	/**
	 * 获取字符串的所有字符的首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getAllFirstChar(String str) {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				sb.append(pinyinArray[0].charAt(0));
			} else {
				sb.append(word);
			}
		}
		return sb.toString().toUpperCase();
	}
}
