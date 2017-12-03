package com.psp.admin.controller.res.bean;


/**
 * 操作工单信息
 **/
public class ROrderLogsBean {
	private Integer lid; // 操作id
	private String oid; // 订单ID
	private String sid; // 销售id
	private String sellerJson; // 销售信息
	private String content; // 操作文本
	private String pid; // 服务商id
	private String providerJson; // 服务商JSON
	private Long createTime; // 创建时间
	private Integer type; // 操作类型0 创建并分配 1 编辑 2 派单 3 上传合同 4 调查反馈 5 归档

	public void setLid(Integer lid) {
 		this.lid = lid;
	}

	public Integer getLid() {
 		return lid;
	}

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

	public void setSellerJson(String sellerJson) {
 		this.sellerJson = sellerJson;
	}

	public String getSellerJson() {
 		return sellerJson;
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

	public void setType(Integer type) {
 		this.type = type;
	}

	public Integer getType() {
 		return type;
	}

}
