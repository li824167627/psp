package com.psp.provider.persist.impl;

import org.springframework.stereotype.Repository;

import com.psp.provider.model.AccountBean;
import com.psp.provider.persist.dao.AccountDao;

@Repository
public class AccountImpl extends BaseImpl implements AccountDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".AccountMapper";

	@Override
	public AccountBean selectOneById(String sid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", sid);
	}

	@Override
	public AccountBean selectOneByPhone(String phone) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneByPhone", phone);
	}

	@Override
	public int updateLoginTime(String aid) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateLoginTime", aid);
	}

}
