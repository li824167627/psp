package com.psp.web.cache.impl;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.psp.web.cache.BaseCacheImpl;
import com.psp.web.cache.dao.ServiceCacheDao;

@Repository
public class ServiceCacheImpl extends BaseCacheImpl implements ServiceCacheDao {
	String KEY_CATEGOTY_ALL = NAME_SPACE + "admin:common:category:all";
	String KEY_CATEGOTY = NAME_SPACE + "admin:common:category:service";

	@Override
	public boolean setAllCategoryCache(String cates) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				String key = KEY_CATEGOTY_ALL;
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
	public String getAllCategoryCache() {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				String key = KEY_CATEGOTY_ALL;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					return new String(value);
				}
				return null;
			}
		});
	}

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
