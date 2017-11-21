package com.psp.admin.persist.dao;

import com.psp.admin.model.AdminBean;

public interface AdminDao {

	AdminBean selectOneById(String sid);

}
