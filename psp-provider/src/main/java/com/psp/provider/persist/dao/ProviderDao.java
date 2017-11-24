package com.psp.provider.persist.dao;

import java.util.List;

import com.psp.provider.model.CategoryBean;
import com.psp.provider.model.ProviderBean;

public interface ProviderDao {

	List<CategoryBean> selectAllCates();

	List<ProviderBean> selectListByCid(Integer categoryId);

	ProviderBean selectOneById(String pid);


}
