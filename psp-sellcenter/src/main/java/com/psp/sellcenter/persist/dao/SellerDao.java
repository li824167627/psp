package com.psp.sellcenter.persist.dao;

import com.psp.sellcenter.model.SellerBean;

public interface SellerDao {

	SellerBean selectOneById(String sid);

	int updateLoginTime(String sid);

	SellerBean selectOneByPhone(String phone);

	int updatePwd(SellerBean user);

	int updateName(SellerBean user);

}
