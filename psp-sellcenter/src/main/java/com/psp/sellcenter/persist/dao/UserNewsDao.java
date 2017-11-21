package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.UserNewsBean;

public interface UserNewsDao {

	int selectUserNewsCount(String sid, int stype, String key, String uid);

	List<UserNewsBean> selectUserNews(int page, int pageSize, String sid, int stype, String key, String uid);

	int insert(UserNewsBean news);

}
