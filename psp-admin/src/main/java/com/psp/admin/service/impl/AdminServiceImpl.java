package com.psp.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.admin.cache.dao.AdminCacheDao;
import com.psp.admin.model.AdminBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminImpl;
	
	@Autowired
	AdminCacheDao adminCacheImpl;
	
	@Override
	public AdminBean getAdminByToken(String token) {
		if (token == null) {
			return null;
		}
		String sid = adminCacheImpl.getAdminIdByToken(token);
		if (sid == null) {
			return null;
		}
		AdminBean user = adminImpl.selectOneById(sid);
		return user;
	}

	@Override
	public AdminBean getAdminById(String sid) {
		AdminBean user = adminImpl.selectOneById(sid);
		return user;
	}

}
