package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 创建服务商账户
 **/
public class UpdateNameParam {
	@NotEmpty(message = "服务商id不能为空")
	private String pid; // 服务商id
	private String name; // 账户名
	@NotEmpty(message = "手机号")
	private String phone; // 手机号
	private String password; // 密码

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
