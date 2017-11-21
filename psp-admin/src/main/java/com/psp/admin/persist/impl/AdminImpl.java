package com.psp.admin.persist.impl;

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

}
