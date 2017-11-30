package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.UserBean;

public interface UserDao {


	UserBean selectUserById(String uid);

	// -======= admin ======
	int selectUserCount(int filteType, int stype, String key, int status);

	List<UserBean> selectUsers(int page, int pageSize, int filteType, int stype, String key, int status);

	int allotUser(UserBean user);

}
