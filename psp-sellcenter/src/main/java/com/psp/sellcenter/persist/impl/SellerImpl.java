package com.psp.sellcenter.persist.impl;

import org.springframework.stereotype.Repository;

import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.persist.dao.SellerDao;

@Repository
public class SellerImpl extends BaseImpl implements SellerDao {

	final String NAME_SPACE = NAME_SPACE_HEADER + ".SellerMapper";

	@Override
	public SellerBean selectOneById(String sid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", sid);
	}

	@Override
	public int updateLoginTime(String sid) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateLoginTime", sid);

	}

	@Override
	public SellerBean selectOneByPhone(String phone) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneByPhone", phone);
	}

	@Override
	public int updatePwd(SellerBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updatePwd", user);
	}

	@Override
	public int updateName(SellerBean user) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateName", user);
	}

}
