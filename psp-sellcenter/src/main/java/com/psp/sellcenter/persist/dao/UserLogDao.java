package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.UserLogBean;

public interface UserLogDao {

	int insert(UserLogBean userlog);

	int selectUserLogsCount(String uid, String key);

	List<UserLogBean> selectUserLogs(String uid, String key);


}
