package com.psp.admin.model;


/**
 * 工单统计阶段数量信息
 **/
public class OrderStageStatisticsBean {
	private Integer underway; // 进行中
	private Integer completed; // 已完成
	private Integer closed; // 已关闭

	public void setUnderway(Integer underway) {
 		this.underway = underway;
	}

	public Integer getUnderway() {
 		return underway;
	}

	public void setCompleted(Integer completed) {
 		this.completed = completed;
	}

	public Integer getCompleted() {
 		return completed;
	}

	public void setClosed(Integer closed) {
 		this.closed = closed;
	}

	public Integer getClosed() {
 		return closed;
	}

}
