package com.psp.web.persist.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.web.model.UserBean;
import com.psp.web.model.UserLogBean;
import com.psp.web.model.UserNewsBean;
import com.psp.web.persist.dao.UserDao;

@Repository
public class UserImpl extends BaseImpl implements UserDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".UserMapper";

	@Override
	public int insert(UserBean user) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", user);
	}

	@Override
	public int insertUserLog(UserLogBean log) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insertUserLog", log);
	}

	@Override
	public int insertUserNews(UserNewsBean news) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insertUserNews", news);
	}

	@Override
	public UserBean selectOneByPhone(String phoneNum) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserByPhone", phoneNum);
	}

	@Override
	public int updateStatus(UserBean user) {
		Map<String, Object> where = new HashMap<>();
		where.put("uid", user.getUid());
		where.put("status", user.getStatus());
		return sqlSessionTemplate.update(NAME_SPACE + ".updateStatus", where);
	}


}
