package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.AreaBean;

public interface AreaDao {

	List<AreaBean> selectListByPCD();

	AreaBean selectById(String p);


}
