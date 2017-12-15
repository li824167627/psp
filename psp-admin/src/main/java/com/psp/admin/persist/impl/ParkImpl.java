package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.ParkBean;
import com.psp.admin.persist.dao.ParkDao;
import com.psp.util.StringUtil;

@Repository
public class ParkImpl extends BaseImpl implements ParkDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".ParkMapper";

	@Override
	public int selectCount(String key, String parkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", key);
		if(!StringUtil.isEmpty(parkId)) {
			params.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectCount", params);
	

	}

	@Override
	public List<ParkBean> selectList(int page, int pageSize, String key, String parkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page * pageSize);
		params.put("length", pageSize);
		params.put("key", key);
		if(!StringUtil.isEmpty(parkId)) {
			params.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectList", params);
	
	}

	@Override
	public int insert(ParkBean park) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", park);
		
	}

	@Override
	public ParkBean selectOneById(String pid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOneById", pid);
	}

	@Override
	public int update(ParkBean park) {
		return sqlSessionTemplate.update(NAME_SPACE + ".update", park);

	}


}
