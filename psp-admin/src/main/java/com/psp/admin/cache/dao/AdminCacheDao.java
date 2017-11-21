package com.psp.admin.cache.dao;

public abstract interface AdminCacheDao {

	String getAdminIdByToken(String token);
	
	String getTOKENByAdminId(String AdminId);

}
