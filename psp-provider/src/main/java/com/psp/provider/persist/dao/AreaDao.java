package com.psp.provider.persist.dao;

import java.util.List;

import com.psp.provider.model.AreaBean;

public interface AreaDao {

	List<AreaBean> selectListByPCD();

	AreaBean selectById(String p);


}
