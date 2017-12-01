package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.ParkBean;

public interface ParkDao {

	int selectCount(String key);

	List<ParkBean> selectList(int page, int pageSize, String key);

	int insert(ParkBean park);

	ParkBean selectOneById(String pid);

	int update(ParkBean park);

}
