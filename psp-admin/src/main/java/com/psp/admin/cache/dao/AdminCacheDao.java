package com.psp.admin.cache.dao;

import com.psp.admin.model.Code;

public abstract interface AdminCacheDao {

	String getAdminIdByToken(String token);
	
	String getTOKENByAdminId(String AdminId);

	Code getLoginCode(String aid);

	boolean setLoginCode(String aid, Code code);

	boolean setAdminIdTOKEN(String sessionId, String aid, long lastTime);

	boolean setImgCode(String imgToken, Code code);
	
	Code getImgCode(String paramString);

	Code getVCode(int type, String phone);

	boolean setVCode(int type, String phone, Code cacheCode);

}
