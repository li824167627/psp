package com.psp.sellcenter.model;

import java.sql.Timestamp;

/**
 * 客户操作日志信息
 **/
public class UserLogBean {

	private Integer lid; // 客户日志id
	private String uid; // 用户
	private String sid; // 销售id
	private String sellerJson; // 销售信息
	private String content; // 操作文本
	private String aid; // 管理员
	private String adminJson; // 管理员信息
	private Timestamp createTime; // 创建时间
	private Integer type; // 操作类型 0 管理员分配 1 新建客户 2 修改客户 3归档客户

	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAdminJson() {
		return adminJson;
	}
	public void setAdminJson(String adminJson) {
		this.adminJson = adminJson;
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
