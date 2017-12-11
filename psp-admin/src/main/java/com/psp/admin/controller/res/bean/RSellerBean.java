package com.psp.admin.controller.res.bean;


/**
 * 销售信息
 **/
public class RSellerBean {
	private String sid; // 销售id
	private String phoneNum; // 销售手机号
	private String username; // 销售昵称
	private String admin; // 创建人
	private Integer status; // 状态1：正常，0：禁用
	private Long createTime; // 创建时间
	private Long lastLoginTime; // 最后登录时间
	private Integer type; // 类型 1：总销售，0：园区销售
	private String parkName; // 所属园区
	private String pid; // 所属园区id

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

	public void setAdmin(String admin) {
 		this.admin = admin;
	}

	public String getAdmin() {
 		return admin;
	}

	public void setStatus(Integer status) {
 		this.status = status;
	}

	public Integer getStatus() {
 		return status;
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

	public void setType(Integer type) {
 		this.type = type;
	}

	public Integer getType() {
 		return type;
	}

	public void setParkName(String parkName) {
 		this.parkName = parkName;
	}

	public String getParkName() {
 		return parkName;
	}

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

}
