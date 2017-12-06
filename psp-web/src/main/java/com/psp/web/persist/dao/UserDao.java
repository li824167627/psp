package com.psp.web.persist.dao;

import com.psp.web.model.UserBean;
import com.psp.web.model.UserLogBean;
import com.psp.web.model.UserNewsBean;

public interface UserDao {

	int insert(UserBean user);

	int insertUserLog(UserLogBean log);

	int insertUserNews(UserNewsBean news);

	UserBean selectOneByPhone(String phoneNum);

	int updateStatus(UserBean user);


}
