package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑销售
 **/
public class EditSellerParam {
	private String sid; // 销售id
	@NotEmpty(message = "名称不能为空")
	private String name; // 销售名称
	private String password; // 密码
	@NotEmpty(message = "手机号不能为空")
	private String phoneNum; // 手机号，根据联系人和手机号生成服务商管理账号

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setPassword(String password) {
 		this.password = password;
	}

	public String getPassword() {
 		return password;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

}
