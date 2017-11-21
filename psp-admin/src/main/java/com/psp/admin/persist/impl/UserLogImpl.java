package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.UserLogBean;
import com.psp.admin.persist.dao.UserLogDao;

@Repository
public class UserLogImpl extends BaseImpl implements UserLogDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".UserLogMapper";

	@Override
	public int insert(UserLogBean userlog) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", userlog);
	}

	@Override
	public int selectUserLogsCount(String uid, String key) {
		Map<String, Object> where = new HashMap<>();
		where.put("uid", uid);
		where.put("key", key);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserLogsCount", where);
	}

	@Override
	public List<UserLogBean> selectUserLogs(String uid, String key) {
		Map<String, Object> where = new HashMap<>();
		where.put("uid", uid);
		where.put("key", key);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectUserLogs", where);
	
	}

}
