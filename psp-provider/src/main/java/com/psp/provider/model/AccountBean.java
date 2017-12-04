package com.psp.provider.model;

import java.sql.Timestamp;

/**
 * 服务商账号信息
 **/
public class AccountBean {
	private String aid; // 服务商账号id
	private String phoneNum; // 销售手机号
	private String username; // 销售昵称
	private Integer status; // 状态1：正常，0：禁用
	private Timestamp lastLoginTime; // 最后登录时间
	private Timestamp createTime; // 创建时间
	private Integer type; // 服务商账号类型：0 员工 1管理账号
	private String pid;// 关联服务商
	private String password;// 服务商

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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
