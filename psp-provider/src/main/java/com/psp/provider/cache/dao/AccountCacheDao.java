package com.psp.provider.cache.dao;

public abstract interface AccountCacheDao {

	String getAccountIdByToken(String token);
	
	String getTOKENByAccountId(String accountId);

}
