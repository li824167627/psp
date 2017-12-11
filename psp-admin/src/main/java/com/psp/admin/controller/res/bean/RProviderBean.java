package com.psp.admin.controller.res.bean;


/**
 * 服务商信息
 **/
public class RProviderBean {
	private String pid; // 服务商pid
	private String name; // 服务商名称
	private String address; // 地址
	private String contact; // 联系人
	private String admin; // 创建人
	private String phoneNum; // 联系电话
	private String content; // 服务商服务内容简介
	private Double score; // 评价得分
	private Long createTime; // 创建时间
	private Integer status; // 状态 0 正常 1 禁用

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setAddress(String address) {
 		this.address = address;
	}

	public String getAddress() {
 		return address;
	}

	public void setContact(String contact) {
 		this.contact = contact;
	}

	public String getContact() {
 		return contact;
	}

	public void setAdmin(String admin) {
 		this.admin = admin;
	}

	public String getAdmin() {
 		return admin;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

	public void setScore(Double score) {
 		this.score = score;
	}

	public Double getScore() {
 		return score;
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
