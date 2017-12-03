package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑管理员
 **/
public class EditAdminParam {
	private String aid; // 管理员id
	@NotEmpty(message = "名称不能为空")
	private String username; // 管理员名称
	private String phoneNum; // 手机号
	private String password; // 密码
	private String confirmPwd; // 确认密码
	private Integer type; // 类型：0 园区运营人员 1 超级管理员
	private String pid; // 

	public void setAid(String aid) {
 		this.aid = aid;
	}

	public String getAid() {
 		return aid;
	}

	public void setUsername(String username) {
 		this.username = username;
	}

	public String getUsername() {
 		return username;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setPassword(String password) {
 		this.password = password;
	}

	public String getPassword() {
 		return password;
	}

	public void setConfirmPwd(String confirmPwd) {
 		this.confirmPwd = confirmPwd;
	}

	public String getConfirmPwd() {
 		return confirmPwd;
	}

	public void setType(Integer type) {
 		this.type = type;
	}

	public Integer getType() {
 		return type;
	}

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

}
