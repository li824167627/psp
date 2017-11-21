package com.psp.sellcenter.controller.res.bean;


/**
 * 工单信息
 **/
public class ROrderBean {
	private String oid; // 工单信息id
	private String orderNo; // 工单号
	private String uid; // 客户id
	private String userJson; // 客户信息
	private String label; // 工单标签
	private String content; // 工单需求描述
	private String pid; // 服务商
	private String providerJson; // 服务商信息
	private Long createTime; // 创建时间
	private Integer status; // 状态

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setOrderNo(String orderNo) {
 		this.orderNo = orderNo;
	}

	public String getOrderNo() {
 		return orderNo;
	}

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setUserJson(String userJson) {
 		this.userJson = userJson;
	}

	public String getUserJson() {
 		return userJson;
	}

	public void setLabel(String label) {
 		this.label = label;
	}

	public String getLabel() {
 		return label;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setProviderJson(String providerJson) {
 		this.providerJson = providerJson;
	}

	public String getProviderJson() {
 		return providerJson;
	}

	public void setCreateTime(Long createTime) {
 		this.createTime = createTime;
	}

	public Long getCreateTime() {
 		return createTime;
	}

	public void setStatus(Integer status) {
 		this.status = status;
	}

	public Integer getStatus() {
 		return status;
	}

}
