package com.psp.admin.cache.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.psp.admin.cache.BaseCacheImpl;
import com.psp.admin.cache.dao.AdminCacheDao;

@Repository
public class AdminrCacheImpl extends BaseCacheImpl implements AdminCacheDao {
	String KEY_IMG_CODE = NAME_SPACE + "imgcode:";
	String KEY_LOGIN_CODE = NAME_SPACE + "logincode:";
	String KEY_VCODE = NAME_SPACE + "vcode:";
	String KEY_TOKEN_UID = NAME_SPACE + "token:adminId:";
	String KEY_UID_TOKEN = NAME_SPACE + "adminId:token:";
	
	@Override
	public String getAdminIdByToken(String token) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				String key = KEY_TOKEN_UID + token;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					connection.expire(key.getBytes(), 24 * 3600);
					String AdminId = new String(value);
					key = KEY_UID_TOKEN + AdminId;
					connection.expire(key.getBytes(), 24 * 3600);
					return AdminId;
				}
				return null;
			}
		});
	}
	
	@Override
	public String getTOKENByAdminId(String AdminId) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = (KEY_UID_TOKEN + AdminId).getBytes();
				byte[] value = connection.get(key);
				if (value != null) {
					return new String(value);
				}
				return null;
			}
		});
	}

}
