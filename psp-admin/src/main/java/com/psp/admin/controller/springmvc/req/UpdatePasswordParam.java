package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 更改密码
 **/
public class UpdatePasswordParam {
	@NotEmpty(message = "密码不能为空")
	private String oldPwd; // 密码
	@NotEmpty(message = "新密码不能为空")
	private String password; // 新密码
	@NotEmpty(message = "确定密码不能为空")
	private String confirmPwd; // 新密码

	public void setOldPwd(String oldPwd) {
 		this.oldPwd = oldPwd;
	}

	public String getOldPwd() {
 		return oldPwd;
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

}
