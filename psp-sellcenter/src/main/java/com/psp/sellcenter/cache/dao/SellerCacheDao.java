package com.psp.sellcenter.cache.dao;

public abstract interface SellerCacheDao {

	String getSellerIdByToken(String token);
	
	String getTOKENBySellerId(String sellerId);

}
