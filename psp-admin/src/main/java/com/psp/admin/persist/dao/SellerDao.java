package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.SellerBean;

public interface SellerDao {

	SellerBean selectOneById(String sid);

	int selectSellerCount();

	List<SellerBean> selectSellers(int page, int pageSize);

	int insert(SellerBean seller);

	int update(SellerBean seller);

	SellerBean selectOneByPhone(String phoneNum);

}
