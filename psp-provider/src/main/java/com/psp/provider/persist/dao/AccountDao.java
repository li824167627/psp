package com.psp.provider.persist.dao;

import com.psp.provider.model.AccountBean;

public interface AccountDao {

	AccountBean selectOneById(String sid);

	AccountBean selectOneByPhone(String phone);

	int updateLoginTime(String aid);

}
