package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.UserBean;

public interface UserDao {

	int selectUserCount2Seller(String sid, int filteType, int stype, String key, int status);

	List<UserBean> selectUsers2Seller(int page, int pageSize, String sid, int filteType, int stype, String key,
			int status);

	UserBean selectUserByPhone(String phoneNum);

	int insert(UserBean user);

	int updateSeller(UserBean user);

	int update(UserBean user);

	UserBean selectUserById(String uid);

	int updateLevel(String uid, int level);

	int updateLabel(String uid, String label);

	int archive(String uid);

	int updateStatus(UserBean user);

}
