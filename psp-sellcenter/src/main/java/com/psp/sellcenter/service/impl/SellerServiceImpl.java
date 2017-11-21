package com.psp.sellcenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.sellcenter.cache.dao.SellerCacheDao;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.persist.dao.SellerDao;
import com.psp.sellcenter.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	SellerCacheDao sellerCacheImpl;
	
	@Override
	public SellerBean getSellerByToken(String token) {
		if (token == null) {
			return null;
		}
		String sid = sellerCacheImpl.getSellerIdByToken(token);
		if (sid == null) {
			return null;
		}
		SellerBean user = sellerImpl.selectOneById(sid);
		return user;
	}

	@Override
	public SellerBean getSellerById(String sid) {
		SellerBean user = sellerImpl.selectOneById(sid);
		return user;
	}

}
