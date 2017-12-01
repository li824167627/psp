package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.UserBean;

public interface UserDao {


	UserBean selectUserById(String uid);

	// -======= admin ======
	int selectUserCount(int filteType, int stype, String key, int isALlot);

	List<UserBean> selectUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot);

	int allotUser(UserBean user);

}
