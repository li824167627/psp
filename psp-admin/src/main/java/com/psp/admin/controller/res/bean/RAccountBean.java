package com.psp.admin.controller.res.bean;


/**
 * 账户信息
 **/
public class RAccountBean {
	private String aid; // 账户aid
	private String phoneNum; // 用户手机号
	private String username; // 用户昵称
	private Integer status; // 状态1：正常，0：禁用
	private Integer type; // 用户类型 0 员工 1 服务商管理员
	private Long createTime; // 创建时间
	private Long lastLoginTime; // 上次登录时间

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

	public void setType(Integer type) {
 		this.type = type;
	}

	public Integer getType() {
 		return type;
	}

	public void setCreateTime(Long createTime) {
 		this.createTime = createTime;
	}

	public Long getCreateTime() {
 		return createTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
 		this.lastLoginTime = lastLoginTime;
	}

	public Long getLastLoginTime() {
 		return lastLoginTime;
	}

}
