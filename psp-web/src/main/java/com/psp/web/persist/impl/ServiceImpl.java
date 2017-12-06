package com.psp.web.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.web.model.CategoryBean;
import com.psp.web.persist.dao.ServiceDao;

@Repository
public class ServiceImpl extends BaseImpl implements ServiceDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".ServiceMapper";

	@Override
	public List<CategoryBean> selectAllCates() {
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectAllCates");
	}

	@Override
	public List<CategoryBean> selectService(String parentId) {
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectService", parentId);
	}

	@Override
	public int selectServiceCountByPid(int parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectServiceCountByPid", params);
	}

	@Override
	public List<CategoryBean> selectServiceByPid(int parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectServiceByPid", params);
	}

	@Override
	public CategoryBean selectServiceById(int cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectServiceById", params);
	
	}


}
