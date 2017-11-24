package com.psp.sellcenter.cache.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.psp.sellcenter.cache.BaseCacheImpl;
import com.psp.sellcenter.cache.dao.SellerCacheDao;

@Repository
public class SellerCacheImpl extends BaseCacheImpl implements SellerCacheDao {
	String KEY_IMG_CODE = "psp:2.0:seller:imgcode:";
	String KEY_LOGIN_CODE = "psp:2.0:seller:logincode:";
	String KEY_VCODE = "psp:2.0:seller:vcode:";
	String KEY_TOKEN_UID = "psp:2.0:seller:token:sellerId:";
	String KEY_UID_TOKEN = "psp:2.0:seller:sellerId:token:";
	
	@Override
	public String getSellerIdByToken(String token) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				String key = KEY_TOKEN_UID + token;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					connection.expire(key.getBytes(), 24 * 3600);
					String sellerId = new String(value);
					key = KEY_UID_TOKEN + sellerId;
					connection.expire(key.getBytes(), 24 * 3600);
					return sellerId;
				}
				return null;
			}
		});
	}
	
	@Override
	public String getTOKENBySellerId(String sellerId) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = (KEY_UID_TOKEN + sellerId).getBytes();
				byte[] value = connection.get(key);
				if (value != null) {
					return new String(value);
				}
				return null;
			}
		});
	}

}
