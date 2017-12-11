package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.UserBean;
import com.psp.admin.persist.dao.UserDao;
import com.psp.util.StringUtil;

@Repository
public class UserImpl extends BaseImpl implements UserDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".UserMapper";


	@Override
	public UserBean selectUserById(String uid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserById", uid);
	}

	@Override
	public int selectUserCount(int filteType, int stype, String key, int isALlot, String sid) {
		Map<String, Object> where = new HashMap<>();
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("isALlot", isALlot);
		if(!StringUtil.isEmpty(sid)) {
			where.put("sid", sid);
		}
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserCount", where);
	}

	@Override
	public List<UserBean> selectUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot, String sid) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("isALlot", isALlot);
		if(!StringUtil.isEmpty(sid)) {
			where.put("sid", sid);
		}
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectUsers", where);
	}

	@Override
	public int allotUser(UserBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".allotUser", user);
	}

	@Override
	public int selectParkUserNum(String pid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectParkUserCount", pid);
	}

}
