package com.psp.provider.cache.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.psp.provider.cache.BaseCacheImpl;
import com.psp.provider.cache.dao.AccountCacheDao;

@Repository
public class AccountCacheImpl extends BaseCacheImpl implements AccountCacheDao {
	String KEY_IMG_CODE = NAME_SPACE + "imgcode:";
	String KEY_LOGIN_CODE = NAME_SPACE + "logincode:";
	String KEY_VCODE = NAME_SPACE + "vcode:";
	String KEY_TOKEN_UID = NAME_SPACE + "token:adminId:";
	String KEY_UID_TOKEN = NAME_SPACE + "adminId:token:";
	
	@Override
	public String getAccountIdByToken(String token) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				String key = KEY_TOKEN_UID + token;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					connection.expire(key.getBytes(), 24 * 3600);
					String accountId = new String(value);
					key = KEY_UID_TOKEN + accountId;
					connection.expire(key.getBytes(), 24 * 3600);
					return accountId;
				}
				return null;
			}
		});
	}

	@Override
	public String getTOKENByAccountId(String accountId) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = (KEY_UID_TOKEN + accountId).getBytes();
				byte[] value = connection.get(key);
				if (value != null) {
					return new String(value);
				}
				return null;
			}
		});
	}

}
