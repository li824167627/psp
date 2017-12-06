package com.psp.sellcenter.cache.dao;

import com.psp.sellcenter.model.Code;

public abstract interface SellerCacheDao {

	String getSellerIdByToken(String token);
	
	String getTOKENBySellerId(String sellerId);

	Code getLoginCode(String phone);
	
	boolean setLoginCode(String aid, Code code);

	boolean setAccountIdTOKEN(String sessionId, String aid, long l);

	boolean setImgCode(String imgToken, Code code);
	
	Code getImgCode(String phone);

}
