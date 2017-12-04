package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.AreaBean;

public interface AreaDao {

	List<AreaBean> selectListByPCD();

	AreaBean selectById(String p);


}
