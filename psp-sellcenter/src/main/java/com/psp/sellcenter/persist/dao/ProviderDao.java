package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.CategoryBean;
import com.psp.sellcenter.model.ProviderBean;

public interface ProviderDao {

	List<CategoryBean> selectAllCates();

	List<ProviderBean> selectListByCid(Integer categoryId);

	ProviderBean selectOneById(String pid);
	
	List<CategoryBean> selectService(Integer parentId);

	List<ProviderBean> selectAll();

	int updateScore(ProviderBean proBean);
}
