package com.psp.sellcenter.cache.impl;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.psp.sellcenter.cache.BaseCacheImpl;
import com.psp.sellcenter.cache.dao.ServiceCacheDao;

@Repository
public class ServiceCacheImpl extends BaseCacheImpl implements ServiceCacheDao {
	String KEY_CATEGOTY = NAME_SPACE + "category";

	@Override
	public boolean setCategoryCache(String cates) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				String key = KEY_CATEGOTY;
				if (cates == null) {
					connection.del(key.getBytes());
				} else {
					if (!connection.exists(key.getBytes())) {
						connection.set(key.getBytes(), cates.getBytes());
						connection.expire(key.getBytes(), 7 * 60 * 60 * 24);
					}
				}
				return Boolean.valueOf(true);
			}
		});
	}

	@Override
	public String getCategoryCache() {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				String key = KEY_CATEGOTY;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					return new String(value);
				}
				return null;
			}
		});
	}

}
