package com.psp.sellcenter.cache.impl;

import org.springframework.stereotype.Repository;

import com.psp.sellcenter.cache.BaseCacheImpl;
import com.psp.sellcenter.cache.dao.UserCacheDao;

@Repository
public class UserCacheImpl extends BaseCacheImpl implements UserCacheDao {
	String KEY_IMG_CODE = "psp:2.0:seller:imgcode:";
	String KEY_LOGIN_CODE = "psp:2.0:seller:logincode:";
	String KEY_VCODE = "psp:2.0:seller:vcode:";
	String KEY_SESSION_UID = "psp:2.0:seller:session:uid:";
	String KEY_UID_SESSION = "psp:2.0:seller:uid:session:";

}
