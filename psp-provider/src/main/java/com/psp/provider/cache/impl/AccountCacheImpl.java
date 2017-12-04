package com.psp.provider.cache.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.psp.provider.cache.BaseCacheImpl;
import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.model.Code;

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
	public boolean setAccountIdTOKEN(String sessionId, String aid, long expireSeconds) {
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

}
