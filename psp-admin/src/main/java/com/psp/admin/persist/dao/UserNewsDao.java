package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.UserNewsBean;

public interface UserNewsDao {

	int selectUserNewsCount(String sid, int stype, String key, String uid);

	List<UserNewsBean> selectUserNews(int page, int pageSize, String sid, int stype, String key, String uid);

}
