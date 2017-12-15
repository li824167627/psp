package com.psp.provider.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public int updatePwd(AccountBean account) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updatePwd", account);
	}

	@Override
	public int updateName(AccountBean account) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateName", account);
	}

	@Override
	public int selectAccountCount(String pid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectAccountCount", pid);
	}

	@Override
	public List<AccountBean> selectAccounts(int page, int pageSize, String pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page * pageSize);
		params.put("length", pageSize);
		params.put("pid", pid);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectAccounts", params);
	}

	@Override
	public int insertAccount(AccountBean account) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insertAccount", account);
	}

	@Override
	public int updateAccount(AccountBean account) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateAccount", account);
	}

}
