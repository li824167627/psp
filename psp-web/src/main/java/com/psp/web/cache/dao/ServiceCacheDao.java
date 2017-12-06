package com.psp.web.cache.dao;

public abstract interface ServiceCacheDao {

	/**
	 * 设置服务分类 缓存
	 * @param cates
	 * @return
	 */
	public abstract boolean setCategoryCache(String cates);
	
	/**
	 * 获取服务分类 缓存
	 * @return
	 */
	public abstract String getCategoryCache();

	/**
	 * 设置全部服务分类 缓存
	 * @param cates
	 * @return
	 */
	public abstract boolean setAllCategoryCache(String cates);
	
	/**
	 * 获取全部服务分类 缓存
	 * @return
	 */
	public abstract String getAllCategoryCache();
	
}
