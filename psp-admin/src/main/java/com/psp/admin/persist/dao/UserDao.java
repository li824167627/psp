package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.UserBean;

public interface UserDao {


	UserBean selectUserById(String uid);

	// -======= admin ======
	int selectUserCount(int filteType, int stype, String key, int isALlot, String sid);

	List<UserBean> selectUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot, String sid);

	int allotUser(UserBean user);

	int selectParkUserNum(String pid);

}
