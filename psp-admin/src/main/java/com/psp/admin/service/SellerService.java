package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RSellerBean;
import com.psp.admin.service.res.PageResult;

public interface SellerService {

	PageResult<RSellerBean> getSellers(int page, int pageSize);

	boolean EditSeller(String adminId, String sid, String name, String password, String phoneNum);

	boolean resetPwd(String adminId, String sid);

	boolean delSeller(String adminId, String sid);
	

}
