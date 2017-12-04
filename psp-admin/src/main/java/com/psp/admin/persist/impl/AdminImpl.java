package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.AdminBean;
import com.psp.admin.persist.dao.AdminDao;

@Repository
public class AdminImpl extends BaseImpl implements AdminDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".AdminMapper";

	@Override
	public AdminBean selectOneById(String sid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", sid);
	}

	@Override
	public int update(AdminBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".update", user);
	}

	@Override
	public int selectAdminCount(String key) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", key);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectAdminCount", params);

	}

	@Override
	public List<AdminBean> selectAdmins(int page, int pageSize, String key) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page * pageSize);
		params.put("length", pageSize);
		params.put("key", key);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectAdmins", params);

	}

	@Override
	public AdminBean selectOneByPhone(String phone) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneByPhone", phone);

	}

	@Override
	public int insert(AdminBean admin) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", admin);

	}

	@Override
	public int updateLoginTime(String aid) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateLoginTime", aid);
	}

}
