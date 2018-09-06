package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 重设密码
 **/
public class ResetPwdParam {
	@NotEmpty(message = "密码不能为空")
	private String oldPwd; // 密码
	@NotEmpty(message = "新密码不能为空")
	private String password; // 新密码
	@NotEmpty(message = "确认密码不能为空")
	private String confirmPwd; // 确认新密码

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
