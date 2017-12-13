package com.psp.admin.controller.res.bean;


/**
 * 客户状态数量信息
 **/
public class RUserStatusStatisticsBean {
	private Integer communicate; // 已沟通
	private Integer uncommunicate; // 待沟通

	public void setCommunicate(Integer communicate) {
 		this.communicate = communicate;
	}

	public Integer getCommunicate() {
 		return communicate;
	}

	public void setUncommunicate(Integer uncommunicate) {
 		this.uncommunicate = uncommunicate;
	}

	public Integer getUncommunicate() {
 		return uncommunicate;
	}

}
