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
	public int selectUserCount2Seller(String sid, int filteType, int stype, String key, int status) {
		Map<String, Object> where = new HashMap<>();
		where.put("sid", sid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("status", status);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserCount2Seller", where);
	}

	@Override
	public List<UserBean> selectUsers2Seller(int page, int pageSize, String sid, int filteType, int stype, String key, int status) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("sid", sid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("status", status);
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

	@Override
	public UserBean selectUserById(String uid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectUserById", uid);
	}

	@Override
	public int updateLevel(String uid, int level) {
		Map<String, Object> where = new HashMap<>();
		where.put("uid", uid);
		where.put("level", level);
		return sqlSessionTemplate.update(NAME_SPACE + ".updateLevel", where);
	}

	@Override
	public int updateLabel(String uid, String label) {
		Map<String, Object> where = new HashMap<>();
		where.put("uid", uid);
		where.put("label", label);
		return sqlSessionTemplate.update(NAME_SPACE + ".updateLabel", where);
	}

	@Override
	public int archive(String uid) {
		return sqlSessionTemplate.update(NAME_SPACE + ".archive", uid);
	}

	@Override
	public int updateStatus(UserBean user) {
		Map<String, Object> where = new HashMap<>();
		where.put("uid", user.getUid());
		where.put("status", user.getStatus());
		return sqlSessionTemplate.update(NAME_SPACE + ".updateStatus", where);
	}

}
