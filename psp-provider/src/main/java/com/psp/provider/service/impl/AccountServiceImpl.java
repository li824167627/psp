package com.psp.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.model.AccountBean;
import com.psp.provider.persist.dao.AccountDao;
import com.psp.provider.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDao accountImpl;
	
	@Autowired
	AccountCacheDao accountCacheImpl;
	
	@Override
	public AccountBean getAccountByToken(String token) {
		if (token == null) {
			return null;
		}
		String sid = accountCacheImpl.getAccountIdByToken(token);
		if (sid == null) {
			return null;
		}
		AccountBean user = accountImpl.selectOneById(sid);
		return user;
	}

	@Override
	public AccountBean getAccountById(String sid) {
		AccountBean user = accountImpl.selectOneById(sid);
		return user;
	}

}
