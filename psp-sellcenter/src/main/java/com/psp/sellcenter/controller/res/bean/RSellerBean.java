package com.psp.sellcenter.controller.res.bean;

/**
 * 销售信息
 **/
public class RSellerBean {
	private String sid; // 销售id
	private String phoneNum; // 销售手机号
	private String letter; // 名称首字母
	private String username; // 销售昵称
	private Integer status; // 状态1：正常，0：禁用
	private Long lastLoginTime; // 最后登录时间

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

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
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

}
