package com.psp.web.persist.dao;

import java.util.List;

import com.psp.web.model.CategoryBean;

public interface ServiceDao {

	List<CategoryBean> selectAllCates();

	List<CategoryBean> selectService(String parentId);

	int selectServiceCountByPid(int parentId);

	List<CategoryBean> selectServiceByPid(int parentId);

	CategoryBean selectServiceById(int cid);



}
