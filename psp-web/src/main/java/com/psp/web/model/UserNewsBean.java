package com.psp.web.model;

import java.sql.Timestamp;

/**
 * 客户信息流信息（预工单）
 **/
public class UserNewsBean {
	private Integer nid; // 客户信息id
	private String label; // 消息标签
	private String content; // 需求描述
	private String uid; // 用户
	private String userJson; // 沟通客户JSON
	private Integer origin; // 来源：0线上（PC） 1 主动沟通
	private String sid; // 当前销售
	private String sellerJson; // 当前销售JSON
	private Timestamp createTime; // 创建时间
	
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserJson() {
		return userJson;
	}
	public void setUserJson(String userJson) {
		this.userJson = userJson;
	}
	public Integer getOrigin() {
		return origin;
	}
	public void setOrigin(Integer origin) {
		this.origin = origin;
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
