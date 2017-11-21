package com.psp.admin.controller.res.bean;


/**
 * 客户信息流信息
 **/
public class RUserNewsBean {
	private Integer nid; // 客户信息id
	private String label; // 消息标签
	private String content; // 需求描述
	private String uid; // 用户
	private String userJson; // 沟通客户JSON
	private Integer origin; // 来源：0线上（PC） 1 主动沟通
	private Long createTime; // 创建时间
	private String sid; // 当前销售
	private String sellerJson; // 当前销售JSON

	public void setNid(Integer nid) {
 		this.nid = nid;
	}

	public Integer getNid() {
 		return nid;
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

	public void setOrigin(Integer origin) {
 		this.origin = origin;
	}

	public Integer getOrigin() {
 		return origin;
	}

	public void setCreateTime(Long createTime) {
 		this.createTime = createTime;
	}

	public Long getCreateTime() {
 		return createTime;
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

}
