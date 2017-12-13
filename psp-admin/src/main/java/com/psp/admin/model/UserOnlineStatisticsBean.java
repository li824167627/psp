package com.psp.admin.model;


/**
 * 客户线上线下数量信息
 **/
public class UserOnlineStatisticsBean {
	private Integer online; // 线上
	private Integer offline; // 线下

	public void setOnline(Integer online) {
 		this.online = online;
	}

	public Integer getOnline() {
 		return online;
	}

	public void setOffline(Integer offline) {
 		this.offline = offline;
	}

	public Integer getOffline() {
 		return offline;
	}

}
