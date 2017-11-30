package com.psp.admin.model;

import java.sql.Timestamp;

/**
 * 销售信息
 **/
public class SellerBean {
	private String sid; // 销售id
	private String phoneNum; // 销售手机号
	private String username; // 销售昵称
	private Integer status; // 状态1：正常，0：禁用
	private Timestamp createTime; // 创建时间
	private Timestamp lastLoginTime; // 最后登录时间
	private String password;//密码

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setUsername(String username) {
 		this.username = username;
	}

	public String getUsername() {
 		return username;
	}

	public void setStatus(Integer status) {
 		this.status = status;
	}

	public Integer getStatus() {
 		return status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
