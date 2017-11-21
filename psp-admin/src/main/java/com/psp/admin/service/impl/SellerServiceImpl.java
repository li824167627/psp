package com.psp.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.admin.model.SellerBean;
import com.psp.admin.persist.dao.SellerDao;
import com.psp.admin.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	SellerDao sellerImpl;
	
	
	@Override
	public SellerBean getSellerByToken(String token) {
		if (token == null) {
			return null;
		}
		return null;
	}

	@Override
	public SellerBean getSellerById(String sid) {
		SellerBean user = sellerImpl.selectOneById(sid);
		return user;
	}

}
