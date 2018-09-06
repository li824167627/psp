package com.psp.sellcenter.cache.dao;

public abstract interface ServiceCacheDao {

	/**
	 * 设置服务分类 缓存
	 * 
	 * @param cates
	 * @return
	 */
	public abstract boolean setCategoryCache(String cates);

	/**
	 * 获取服务分类 缓存
	 * 
	 * @return
	 */
	public abstract String getCategoryCache();

}
