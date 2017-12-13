package com.psp.admin.model;


/**
 * 工单统计状态数量信息
 **/
public class OrderStatusStatisticsBean {
	private Integer completed; // 已完成
	private Integer toAllot; // 待分配
	private Integer pending; // 待处理
	private Integer accept; // 已接收
	private Integer toContract; // 合同已上传
	private Integer toApplyCompelete; // 申请完成
	private Integer toFeedback; // 待反馈
	private Integer refuse; // 拒绝完成
	private Integer toApplyFinished; // 申请终止
	private Integer closed; // 已关闭
	
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	public Integer getToAllot() {
		return toAllot;
	}
	public void setToAllot(Integer toAllot) {
		this.toAllot = toAllot;
	}
	public Integer getPending() {
		return pending;
	}
	public void setPending(Integer pending) {
		this.pending = pending;
	}
	public Integer getAccept() {
		return accept;
	}
	public void setAccept(Integer accept) {
		this.accept = accept;
	}
	public Integer getToContract() {
		return toContract;
	}
	public void setToContract(Integer toContract) {
		this.toContract = toContract;
	}
	public Integer getToApplyCompelete() {
		return toApplyCompelete;
	}
	public void setToApplyCompelete(Integer toApplyCompelete) {
		this.toApplyCompelete = toApplyCompelete;
	}
	public Integer getToFeedback() {
		return toFeedback;
	}
	public void setToFeedback(Integer toFeedback) {
		this.toFeedback = toFeedback;
	}
	public Integer getRefuse() {
		return refuse;
	}
	public void setRefuse(Integer refuse) {
		this.refuse = refuse;
	}
	public Integer getToApplyFinished() {
		return toApplyFinished;
	}
	public void setToApplyFinished(Integer toApplyFinished) {
		this.toApplyFinished = toApplyFinished;
	}
	public Integer getClosed() {
		return closed;
	}
	public void setClosed(Integer closed) {
		this.closed = closed;
	}

}
