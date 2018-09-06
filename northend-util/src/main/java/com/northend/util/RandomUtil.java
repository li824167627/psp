package com.northend.util;

import java.util.Random;

/**
 * 随机码生成包
 * 
 * @author candy
 *
 */
public class RandomUtil {
	/**
	 * 生成随机字母数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomCharAndNum(int length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				int choice = random.nextBoolean() ? 65 : 97; // 取得65大写字母还是97小写字母
				str += (char) (choice + random.nextInt(26));
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	public static String getRandomCharAndNum(int length, int upperOrLower) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				int choice = upperOrLower == 1 ? 65 : 97; // 取得65大写字母还是97小写字母
				str += (char) (choice + random.nextInt(26));
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	/**
	 * 生成随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNum(int length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str += String.valueOf(random.nextInt(10));
		}
		return str;
	}

	/**
	 * 生成随机字母
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomChar(int length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int choice = random.nextBoolean() ? 65 : 97; // 取得65大写字母还是97小写字母
			str += (char) (choice + random.nextInt(26));
		}
		return str;
	}
}
