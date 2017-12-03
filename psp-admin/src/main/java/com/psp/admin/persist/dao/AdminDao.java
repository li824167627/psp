package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.AdminBean;

public interface AdminDao {

	AdminBean selectOneById(String sid);

	int update(AdminBean user);

	int selectAdminCount(String key);

	List<AdminBean> selectAdmins(int page, int pageSize, String key);

	AdminBean selectOneByPhone(String phone);

	int insert(AdminBean admin);

}
