package com.psp.admin.persist.impl;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.SellerBean;
import com.psp.admin.persist.dao.SellerDao;

@Repository
public class SellerImpl extends BaseImpl implements SellerDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".SellerMapper";

	@Override
	public SellerBean selectOneById(String sid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", sid);
	}

}
