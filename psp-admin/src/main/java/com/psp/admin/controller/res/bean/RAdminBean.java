package com.psp.admin.controller.res.bean;


/**
 * 管理员信息
 **/
public class RAdminBean {
	private String aid; // 管理员id
	private String phoneNum; // 销售手机号
	private String username; // 销售昵称
	private Integer status; // 状态1：正常，0：禁用
	private Long lastLoginTime; // 最后登录时间
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

	public void setLastLoginTime(Long lastLoginTime) {
 		this.lastLoginTime = lastLoginTime;
	}

	public Long getLastLoginTime() {
 		return lastLoginTime;
	}

	public void setType(Integer type) {
 		this.type = type;
	}

	public Integer getType() {
 		return type;
	}

	public RAdminBean getDemoValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
