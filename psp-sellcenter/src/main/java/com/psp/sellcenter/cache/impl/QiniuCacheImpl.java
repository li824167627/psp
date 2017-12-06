package com.psp.sellcenter.cache.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import com.psp.sellcenter.cache.BaseCacheImpl;
import com.psp.sellcenter.cache.dao.QiniuCache;

@Component
public class QiniuCacheImpl extends BaseCacheImpl implements QiniuCache {
	final String KEY_QINIU_TOKEN = NAME_SPACE + "qiniu:token";

	@Override
	public void setToken(String value, long expireSeconds) {
		redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = KEY_QINIU_TOKEN.getBytes();
				connection.set(key, value.getBytes());
				connection.expire(key, expireSeconds);

				return true;
			}
		});
	}

	@Override
	public String getToken() {
		return redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = KEY_QINIU_TOKEN.getBytes();
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					if (value != null) {
						return new String(value);
					}
				}
				return null;
			}
		});
	}

}
