package com.psp.admin.service;

import com.psp.admin.model.AdminBean;

public interface AdminService {
	
	/**
	 * 根据token查询销售
	 * @param token
	 * @return
	 */
	AdminBean getAdminByToken(String token);
	
	/**
	 * 根据id查询销售
	 * @param id
	 * @return
	 */
	AdminBean getAdminById(String id);

}
