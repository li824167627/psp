package com.psp.admin.model;

import java.sql.Timestamp;

/**
 * 工单信息
 **/
public class OrderFeedbackBean {
	private Integer fid;
	private String oid; // 工单信息id
	private String sid; // 当前销售id
	private String pid; // 服务商
	private String content; // 服务商
	private Double averageScore;// 获得的平均分
	private String serviceScore;// 评分json
	private Timestamp createTime;// 反馈时间
	private String suggestion;// 意见建议
	private SellerBean seller;
	private ProviderBean provider;// 服务商信息
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}
	public String getServiceScore() {
		return serviceScore;
	}
	public void setServiceScore(String serviceScore) {
		this.serviceScore = serviceScore;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public SellerBean getSeller() {
		return seller;
	}
	public void setSeller(SellerBean seller) {
		this.seller = seller;
	}
	public ProviderBean getProvider() {
		return provider;
	}
	public void setProvider(ProviderBean provider) {
		this.provider = provider;
	}

}
