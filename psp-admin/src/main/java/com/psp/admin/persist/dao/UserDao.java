package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.UserBean;
import com.psp.admin.model.UserLevelStatisticsBean;
import com.psp.admin.model.UserOnlineStatisticsBean;
import com.psp.admin.model.UserStatusStatisticsBean;

public interface UserDao {


	UserBean selectUserById(String uid);

	// -======= admin ======
	int selectUserCount(int filteType, int stype, String key, int isALlot, String sid, String parkId);

	List<UserBean> selectUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot, String sid, String parkId);

	int allotUser(UserBean user);

	int selectParkUserNum(String pid);

	UserLevelStatisticsBean selectLevelCount(String adminId);

	UserStatusStatisticsBean selectStatusCount(String adminId);

	UserOnlineStatisticsBean selectOnlineCount(String adminId);

}
