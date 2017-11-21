package com.psp.sellcenter.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.sellcenter.model.UserBean;
import com.psp.sellcenter.persist.dao.UserDao;

@Repository
public class UserImpl extends BaseImpl implements UserDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".UserMapper";

	@Override
	public int selectUserCount2Seller(String sid, int filteType, int stype, String key) {
		Map<String, Object> where = new HashMap<>();
		where.put("sid", sid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserCount2Seller", where);
	}

	@Override
	public List<UserBean> selectUsers2Seller(int page, int pageSize, String sid, int filteType, int stype, String key) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("sid", sid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectUsers2Seller", where);
	}

	@Override
	public UserBean selectUserByPhone(String phoneNum) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserByPhone", phoneNum);
	}

	@Override
	public int insert(UserBean user) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", user);
	}

	@Override
	public int updateSeller(UserBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateSeller", user);
	}

	@Override
	public int update(UserBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".update", user);
	}

}
