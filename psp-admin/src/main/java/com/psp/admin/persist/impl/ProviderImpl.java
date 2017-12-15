package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.AccountBean;
import com.psp.admin.model.ProviderBean;
import com.psp.admin.model.ProviderServiceBean;
import com.psp.admin.persist.dao.ProviderDao;

@Repository
public class ProviderImpl extends BaseImpl implements ProviderDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".ProviderMapper";

	@Override
	public int insert(ProviderBean provider) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", provider);

	}

	@Override
	public AccountBean selectAccountByPhone(String phoneNum) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectAccountByPhone", phoneNum);
		
		
	}

	@Override
	public int insertAccount(AccountBean account) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insertAccount", account);
	}

	@Override
	public int selectProviderCount(int cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectProviderCount", params);
	}

	@Override
	public List<ProviderBean> selectProviders(int page, int pageSize, int cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page * pageSize);
		params.put("length", pageSize);
		params.put("cid", cid);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectProviders", params);
	
	}

	@Override
	public ProviderBean selectOneById(String pid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", pid);
		
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
	public AccountBean selectAccountById(String aid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectAccountById", aid);
		
	}

	@Override
	public int updateAccount(AccountBean account) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateAccount", account);

	}

	@Override
	public int insertService(List<ProviderServiceBean> pservices) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insertService", pservices);

	}

	@Override
	public int update(ProviderBean provider) {
		return sqlSessionTemplate.update(NAME_SPACE + ".update", provider);

	}

	@Override
	public int delHisService(String pid) {
		return sqlSessionTemplate.delete(NAME_SPACE + ".delHisService", pid);
	}

	@Override
	public int addService(String pid, String cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("cid", cid);
		return sqlSessionTemplate.insert(NAME_SPACE + ".addService", params);
	}

	@Override
	public int delService(String pid, String cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("cid", cid);
		return sqlSessionTemplate.delete(NAME_SPACE + ".delService", params);
	}

	@Override
	public ProviderServiceBean selectServiceByPidCid(String pid, String cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("cid", cid);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectServiceByPidCid", params);
	}

	@Override
	public int updateStatus(ProviderBean provider) {
		return sqlSessionTemplate.update(NAME_SPACE + ".updateStatus", provider);
	}


}
