package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.SellerBean;
import com.psp.admin.persist.dao.SellerDao;
import com.psp.util.StringUtil;

@Repository
public class SellerImpl extends BaseImpl implements SellerDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".SellerMapper";

	@Override
	public SellerBean selectOneById(String sid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", sid);
	}

	@Override
	public int selectSellerCount(String pid, String key, String parkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(pid)) {
			params.put("pid", pid);
		}
		if(!StringUtil.isEmpty(key)) {
			params.put("key", key);
		}
		if(!StringUtil.isEmpty(parkId)) {
			params.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectSellerCount", params);

	}

	@Override
	public List<SellerBean> selectSellers(int page, int pageSize, String pid, String key, String parkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page * pageSize);
		params.put("length", pageSize);
		if(!StringUtil.isEmpty(pid)) {
			params.put("pid", pid);
		}
		if(!StringUtil.isEmpty(key)) {
			params.put("key", key);
		}
		if(!StringUtil.isEmpty(parkId)) {
			params.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectSellers", params);
	
	}

	@Override
	public int insert(SellerBean seller) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", seller);
	}

	@Override
	public int update(SellerBean seller) {
		return sqlSessionTemplate.update(NAME_SPACE + ".update", seller);

	}

	@Override
	public SellerBean selectOneByPhone(String phoneNum) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneByPhone", phoneNum);
	}

}
