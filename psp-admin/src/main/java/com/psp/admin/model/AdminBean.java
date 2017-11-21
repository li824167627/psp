package com.psp.admin.model;

import java.sql.Timestamp;

/**
 * 管理员信息
 **/
public class AdminBean {
	private String aid; // 管理员id
	private String phoneNum; // 销售手机号
	private String username; // 销售昵称
	private Integer status; // 状态1：正常，0：禁用
	private Timestamp lastLoginTime; // 最后登录时间
	private Timestamp createTime; // 创建时间
	private Integer type; // 管理员类型：0 园区运营人员 1超级管理员

	public void setAid(String aid) {
 		this.aid = aid;
	}

	public String getAid() {
 		return aid;
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

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
