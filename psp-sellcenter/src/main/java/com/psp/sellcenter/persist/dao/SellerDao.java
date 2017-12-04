package com.psp.sellcenter.persist.dao;

import com.psp.sellcenter.controller.res.bean.RSellerBean;
import com.psp.sellcenter.model.SellerBean;

public interface SellerDao {

	SellerBean selectOneById(String sid);

	int updateLoginTime(String sid);

	RSellerBean selectOneByPhone(String phone);

}
