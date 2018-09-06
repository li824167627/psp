package com.northend.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 */
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * sha256Hex加密
	 * 
	 * @param data
	 * @return
	 */
	public static String sha256Hex(String data) {
		return DigestUtils.sha256Hex(data);
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.sha256Hex("123456"));
	}
}
