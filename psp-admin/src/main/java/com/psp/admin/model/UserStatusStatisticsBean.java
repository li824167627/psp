package com.psp.admin.model;


/**
 * 客户状态数量信息
 **/
public class UserStatusStatisticsBean {
	private Integer communicate; // 已沟通
	private Integer uncommunicate; // 待沟通
	public Integer getCommunicate() {
		return communicate;
	}
	public void setCommunicate(Integer communicate) {
		this.communicate = communicate;
	}
	public Integer getUncommunicate() {
		return uncommunicate;
	}
	public void setUncommunicate(Integer uncommunicate) {
		this.uncommunicate = uncommunicate;
	}
}
