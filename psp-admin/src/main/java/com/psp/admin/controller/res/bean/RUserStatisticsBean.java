package com.psp.admin.controller.res.bean;


/**
 * 客户统计信息
 **/
public class RUserStatisticsBean {
	private RUserLevelStatisticsBean level; // 客户级别数量信息
	private RUserStatusStatisticsBean status; // 客户状态数量信息
	private RUserOnlineStatisticsBean online; // 客户线上线下数量信息

	public void setLevel(RUserLevelStatisticsBean level) {
 		this.level = level;
	}

	public RUserLevelStatisticsBean getLevel() {
 		return level;
	}

	public void setStatus(RUserStatusStatisticsBean status) {
 		this.status = status;
	}

	public RUserStatusStatisticsBean getStatus() {
 		return status;
	}

	public void setOnline(RUserOnlineStatisticsBean online) {
 		this.online = online;
	}

	public RUserOnlineStatisticsBean getOnline() {
 		return online;
	}

}
