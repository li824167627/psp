package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.UserLogBean;

public interface UserLogDao {

	int insert(UserLogBean userlog);

	int selectUserLogsCount(String uid, String key);

	List<UserLogBean> selectUserLogs(String uid, String key);


}
