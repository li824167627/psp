package com.psp.admin.cache.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.psp.admin.cache.BaseCacheImpl;
import com.psp.admin.cache.dao.AdminCacheDao;
import com.psp.admin.model.Code;

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

	@Override
	public boolean setLoginCode(String phone, Code vcode) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				String key = KEY_LOGIN_CODE + phone;
				String data = JSON.toJSONString(vcode);
				connection.set(key.getBytes(), data.getBytes());
				connection.expire(key.getBytes(), 5 * 60);
				return Boolean.valueOf(true);
			}
		});
	}

	@Override
	public Code getLoginCode(String phone) {
		return redisTemplate.execute(new RedisCallback<Code>() {
			@Override
			public Code doInRedis(RedisConnection connection) {
				Code code = new Code();
				String key = KEY_LOGIN_CODE + phone;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					code = (Code) JSON.parseObject(value, Code.class);
					return code;
				}
				return null;
			}
		});
	}

	@Override
	public boolean setAdminIdTOKEN(String sessionId, String aid, long expireSeconds) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				String key = KEY_UID_TOKEN + aid;

				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					connection.del(new byte[][] { (KEY_TOKEN_UID + new String(value)).getBytes() });
				}
				connection.set(key.getBytes(), sessionId.getBytes());
				connection.expire(key.getBytes(), expireSeconds);

				key = KEY_TOKEN_UID + sessionId;
				connection.set(key.getBytes(), aid.getBytes());
				connection.expire(key.getBytes(), expireSeconds);
				return Boolean.valueOf(true);
			}
		});
	}

	@Override
	public boolean setImgCode(String imgToken, Code code) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				String key = KEY_IMG_CODE + imgToken;
				if (!connection.exists(key.getBytes())) {
					String data = JSON.toJSONString(code);
					connection.set(key.getBytes(), data.getBytes());
					connection.expire(key.getBytes(), 5 * 60);
				}
				return Boolean.valueOf(true);
			}
		});
	}

	@Override
	public Code getImgCode(String username) {
		return redisTemplate.execute(new RedisCallback<Code>() {
			@Override
			public Code doInRedis(RedisConnection connection) {
				Code code = new Code();
				String key = KEY_IMG_CODE + username;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					code = (Code) JSON.parseObject(value, Code.class);
					return code;
				}
				return null;
			}
		});
	}

	@Override
	public Code getVCode(int type, String phone) {
		return redisTemplate.execute(new RedisCallback<Code>() {
			@Override
			public Code doInRedis(RedisConnection connection) {

				Code code = new Code();
				String key = KEY_VCODE + type + ":" + phone;
				byte[] value = connection.get(key.getBytes());
				if (value != null) {
					code = (Code) JSON.parseObject(value, Code.class);
					return code;
				}
				return null;

			}
		});
	}

	@Override
	public boolean setVCode(int type, String phone, Code cacheCode) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) {
				String data = JSON.toJSONString(cacheCode);
				String key = KEY_VCODE + type + ":" + phone;
				connection.set(key.getBytes(), data.getBytes());
				connection.expire(key.getBytes(), 5 * 60);
				return true;
			}
		});
	}

}
