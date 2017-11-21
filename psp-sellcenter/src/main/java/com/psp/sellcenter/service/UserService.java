package com.psp.sellcenter.service;

import com.psp.sellcenter.controller.res.bean.RUserBean;
import com.psp.sellcenter.service.res.PageResult;

public interface UserService {

	/**
	 * 获取销售下所有用户
	 * @param sid
	 * @param pageSize 
	 * @param page 
	 * @param key 
	 * @param stype 
	 * @param filteType 
	 * @return
	 */
	PageResult<RUserBean> getUsers2Seller(String sid, int page, int pageSize, int filteType, int stype, String key);
	
	/**
	 * 新建用户
	 * @param sid
	 * @param name
	 * @param phoneNum
	 * @param companyName
	 * @param position
	 * @param label
	 * @param isUpdate
	 * @param isClaim
	 * @return
	 */
	RUserBean addUser(String sid, String name, String phoneNum, String companyName, String position, String label,
			int isUpdate, int isClaim);

}
