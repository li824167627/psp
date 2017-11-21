package com.psp.sellcenter.persist.dao;

import com.psp.sellcenter.model.SellerBean;

public interface SellerDao {

	SellerBean selectOneById(String sid);

}
