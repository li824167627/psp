package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.CategoryBean;
import com.psp.admin.persist.dao.ServiceDao;

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
	public int insert(CategoryBean cate) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", cate);
	}

	@Override
	public CategoryBean selectServiceById(int cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectServiceById", params);
	
	}

	@Override
	public int update(CategoryBean cate) {
		return sqlSessionTemplate.update(NAME_SPACE + ".update", cate);
	}

	@Override
	public List<CategoryBean> selectProviderCates(String pid) {
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectProviderCates", pid);
	}

	@Override
	public List<CategoryBean> selectServiceByCids(List<CategoryBean> provider) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(provider.size() > 0 )
			params.put("provider", provider);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectServiceByCids", params);
	}

	@Override
	public int selectProviderCountByCid(int cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectProviderCountByCid", params);
	}

	@Override
	public int deleteService(int cid) {
		return sqlSessionTemplate.delete(NAME_SPACE + ".deleteService", cid);
	}


}
