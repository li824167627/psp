package com.psp.admin.service;

import com.psp.admin.model.SellerBean;

public interface SellerService {
	
	/**
	 * 根据token查询销售
	 * @param token
	 * @return
	 */
	SellerBean getSellerByToken(String token);
	
	/**
	 * 根据id查询销售
	 * @param id
	 * @return
	 */
	SellerBean getSellerById(String id);

}
