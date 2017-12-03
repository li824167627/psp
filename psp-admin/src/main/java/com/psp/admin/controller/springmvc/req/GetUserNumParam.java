package com.psp.admin.controller.springmvc.req;



/**
 * 获取客户数量
 **/
public class GetUserNumParam {
	private String isAllot; // 搜索客户状态，0:全部1:待分配，2已分配

	public void setIsAllot(String isAllot) {
 		this.isAllot = isAllot;
	}

	public String getIsAllot() {
 		return isAllot;
	}

}
