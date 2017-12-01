package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.UserBean;
import com.psp.admin.persist.dao.UserDao;

@Repository
public class UserImpl extends BaseImpl implements UserDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".UserMapper";


	@Override
	public UserBean selectUserById(String uid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserById", uid);
	}

	@Override
	public int selectUserCount(int filteType, int stype, String key, int isALlot) {
		Map<String, Object> where = new HashMap<>();
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("isALlot", isALlot);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserCount", where);
	}

	@Override
	public List<UserBean> selectUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("isALlot", isALlot);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectUsers", where);
	}

	@Override
	public int allotUser(UserBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".allotUser", user);
	}

}
