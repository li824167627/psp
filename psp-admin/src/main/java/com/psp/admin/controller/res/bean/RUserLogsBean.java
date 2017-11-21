package com.psp.admin.controller.res.bean;


/**
 * 操作客户信息
 **/
public class RUserLogsBean {
	private Integer lid; // 操作id
	private String uid; // 用户
	private String sid; // 销售id
	private String sellerJson; // 销售信息
	private String content; // 操作文本
	private String aid; // 管理员
	private String adminJson; // 管理员信息
	private Long createTime; // 创建时间
	private Integer type; // 操作类型0管理员分配 1 新建客户 2 修改客户 3归档客户 4 设置评级 5编辑标签 6开始沟通

	public void setLid(Integer lid) {
 		this.lid = lid;
	}

	public Integer getLid() {
 		return lid;
	}

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
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

	public void setAid(String aid) {
 		this.aid = aid;
	}

	public String getAid() {
 		return aid;
	}

	public void setAdminJson(String adminJson) {
 		this.adminJson = adminJson;
	}

	public String getAdminJson() {
 		return adminJson;
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
