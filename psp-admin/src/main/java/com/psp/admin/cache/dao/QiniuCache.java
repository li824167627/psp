package com.psp.admin.cache.dao;

public interface QiniuCache {
	/**
	 * 设置七牛的token
	 * 
	 * @param value
	 * @param expireSeconds
	 */
	void setToken(String value, long expireSeconds);

	/**
	 * 获取七牛的token
	 * 
	 * @return
	 */
	String getToken();
}
