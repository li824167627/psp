package com.psp.sellcenter.model;

import java.sql.Timestamp;

/**
 * 工单操作日志信息
 **/
public class OrderLogBean {

	private Integer lid; // 工单日志id
	private String oid; // 订单ID
	private String sid; // 销售id
	private String sellerJson; // 销售信息
	private String content; // 操作文本
	private String pid; // 管理员
	private String providerJson; // 管理员信息
	private Timestamp createTime; // 创建时间
	private Integer type; // 操作类型 0 创建并分配 1 编辑 2 派单 3 上传合同 4 调查反馈 5 归档

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
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

	public String getSellerJson() {
		return sellerJson;
	}

	public void setSellerJson(String sellerJson) {
		this.sellerJson = sellerJson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProviderJson() {
		return providerJson;
	}

	public void setProviderJson(String providerJson) {
		this.providerJson = providerJson;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
