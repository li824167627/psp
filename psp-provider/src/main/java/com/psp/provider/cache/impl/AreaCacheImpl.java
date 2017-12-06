package com.psp.provider.cache.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.psp.provider.cache.BaseCacheImpl;
import com.psp.provider.cache.dao.AreaCache;
import com.psp.provider.model.AreaBean;
import com.psp.provider.persist.dao.AreaDao;
import com.psp.util.StringUtil;

@Component
public class AreaCacheImpl extends BaseCacheImpl implements AreaCache {

	final String KEY_AREA_PCD = NAME_SPACE + "area:pcd";

	final String KEY_AREA_ID_SUBAREA = NAME_SPACE + "area:id:subArea:";

	final String KEY_AREA_ID = NAME_SPACE + "area:id:";

	@Autowired
	AreaDao areaImpl;

	@Override
	public String getAreaPCD() {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = KEY_AREA_PCD.getBytes();
				byte[] value = con.get(key);
				if (value != null && value.length > 0) {
					return new String(value);
				}
				return null;
			}
		});
	}

	@Override
	public boolean setAreaPCD(String content) {
		if (StringUtil.isEmpty(content)) {
			return true;
		}
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = KEY_AREA_PCD.getBytes();
				byte[] value = content.getBytes();
				con.set(key, value);
				return true;
			}
		});
	}

	@Override
	public String getSubAreaById(String id) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = (KEY_AREA_ID_SUBAREA + id).getBytes();
				byte[] value = con.get(key);
				if (value != null && value.length > 0) {
					return new String(value);
				}
				return null;
			}
		});
	}

	@Override
	public boolean setSubAreaById(String id, String content) {
		if (StringUtil.isEmpty(content)) {
			return true;
		}
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = (KEY_AREA_ID_SUBAREA + id).getBytes();
				byte[] value = content.getBytes();
				con.set(key, value);
				return true;
			}
		});
	}

	@Override
	public List<String> getPCDSList(String p, String c, String d) {
		List<String> result = new ArrayList<>();
		return redisTemplate.execute(new RedisCallback<List<String>>() {
			@Override
			public List<String> doInRedis(RedisConnection con) throws DataAccessException {
				getpcds(p, result, con);
				getpcds(c, result, con);
				getpcds(d, result, con);
				return result;
			}

			private void getpcds(String p, List<String> result, RedisConnection con) {
				byte[] key = (KEY_AREA_ID + p).getBytes();
				byte[] value = con.get(key);
				String name = "";
				if (value != null && value.length > 0) {
					AreaBean pArea = JSON.parseObject(value, AreaBean.class);
					if (pArea != null) {
						name = pArea.getName();
					}
				} else {
					AreaBean areaBean = areaImpl.selectById(p);
					if (areaBean != null) {
						name = areaBean.getName();
						con.set(key, JSON.toJSON(areaBean).toString().getBytes());
					}
				}
				result.add(name);
			}
		});
	}

}
