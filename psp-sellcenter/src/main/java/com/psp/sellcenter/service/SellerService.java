package com.psp.sellcenter.service;

import com.psp.sellcenter.controller.res.bean.RSellerBean;
import com.psp.sellcenter.model.SellerBean;

public interface SellerService {

	/**
	 * 根据token查询销售
	 * 
	 * @param token
	 * @return
	 */
	SellerBean getSellerByToken(String token);

	/**
	 * 根据id查询销售
	 * 
	 * @param id
	 * @return
	 */
	SellerBean getSellerById(String id);

	RSellerBean login(String sessionId, String phone, String pwd, String vcode, String device, String ip);

	boolean resetPwd(String uid, String pwd, String newPwd, String subPwd);

	/**
	 * 更新用户名
	 * 
	 * @param uid
	 * @param name
	 * @return
	 */
	RSellerBean updateName(String uid, String name);

}
