package com.psp.admin.controller.springmvc.req;



/**
 * 创建服务商账户
 **/
public class UpdateNameParam {
	private String name; // 账户名
	private String phone; // 手机号
	private String password; // 手机号

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setPhone(String phone) {
 		this.phone = phone;
	}

	public String getPhone() {
 		return phone;
	}

	public void setPassword(String password) {
 		this.password = password;
	}

	public String getPassword() {
 		return password;
	}

}
