package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.UserNewsBean;
import com.psp.admin.persist.dao.UserNewsDao;

@Repository
public class UserNewsImpl extends BaseImpl implements UserNewsDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".UserNewsMapper";

	@Override
	public int selectUserNewsCount(String sid, int stype, String key, String uid) {
		Map<String, Object> where = new HashMap<>();
		where.put("sid", sid);
		where.put("stype", stype);
		where.put("key", key);
		where.put("uid", uid);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserNewsCount", where);

	}

	@Override
	public List<UserNewsBean> selectUserNews(int page, int pageSize, String sid, int stype, String key, String uid) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("sid", sid);
		where.put("stype", stype);
		where.put("key", key);
		where.put("uid", uid);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectUserNews", where);
	}

}
