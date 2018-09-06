package com.northend.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

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

	/**
	 * 取第一个汉字的第一个字符 
	 * @Title: getFirstLetter 
	 * @Description: TODO 
	 * @return String @throws
	 */
	public static String getFirstLetter(String ChineseLanguage) {
		char[] cl_chars = ChineseLanguage.trim().toCharArray();
		String hanyupinyin = "";
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		try {
			String str = String.valueOf(cl_chars[0]);
			if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
				hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(cl_chars[0], defaultFormat)[0].substring(0, 1);
				;
			} else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
				hanyupinyin += cl_chars[0];
			} else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

				hanyupinyin += cl_chars[0];
			} else {// 否则不转换
					
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			System.out.println("字符不能转成汉语拼音");
		}
		return hanyupinyin;
	}
	
	/**
	 * 获取拼音
	 * @param ChineseLanguage
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getWordSpell(String str) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer sb = new StringBuffer();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word, defaultFormat);
			if (pinyinArray != null) {
				sb.append(pinyinArray[0]);
			} else {
				sb.append(word);
			}
		}
		return sb.toString().toUpperCase();
	}
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		String a = getHeadFirstChar("程蕾");
		System.out.println(a);
	}
}
