package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 确认找回密码验证码
 **/
public class ConfirmFindPwdCodeParam {
	@NotEmpty(message = "手机号不能为空")
	private String account; // 手机或邮箱
	@NotEmpty(message = "验证码不能为空")
	private String vcode; // 验证码

	public void setAccount(String account) {
 		this.account = account;
	}

	public String getAccount() {
 		return account;
	}

	public void setVcode(String vcode) {
 		this.vcode = vcode;
	}

	public String getVcode() {
 		return vcode;
	}

}
