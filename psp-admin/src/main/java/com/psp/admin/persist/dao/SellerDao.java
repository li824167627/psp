package com.psp.admin.persist.dao;

import com.psp.admin.model.SellerBean;

public interface SellerDao {

	SellerBean selectOneById(String sid);

}
