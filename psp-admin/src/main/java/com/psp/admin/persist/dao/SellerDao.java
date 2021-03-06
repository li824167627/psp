package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.SellerBean;

public interface SellerDao {

	SellerBean selectOneById(String sid);

	int selectSellerCount(String pid, String key, String parkId);

	List<SellerBean> selectSellers(int page, int pageSize, String pid, String key, String parkId);

	int insert(SellerBean seller);

	int update(SellerBean seller);

	SellerBean selectOneByPhone(String phoneNum);

}
