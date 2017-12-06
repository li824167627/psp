package com.psp.provider.cache.dao;

import com.psp.provider.model.Code;

public abstract interface AccountCacheDao {

	String getAccountIdByToken(String token);
	
	String getTOKENByAccountId(String accountId);

	Code getLoginCode(String phone);
	
	boolean setLoginCode(String aid, Code code);

	boolean setAccountIdTOKEN(String sessionId, String aid, long l);

	boolean setImgCode(String imgToken, Code code);
	
	Code getImgCode(String username);
}
