package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 重设密码
 **/
public class ResetPwdParam {
	@NotEmpty(message = "手机号不能为空")
	private String account; // 手机或邮箱
	@NotEmpty(message = "密码不能为空")
	private String oldPwd; // 密码
	@NotEmpty(message = "新密码不能为空")
	private String password; // 新密码
	@NotEmpty(message = "确认密码不能为空")
	private String confirmPwd; // 确认新密码
	private String imgCode; // 图形验证码
	private String imgKey; // 获取时返回的key

	public void setAccount(String account) {
 		this.account = account;
	}

	public String getAccount() {
 		return account;
	}

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

	public void setImgCode(String imgCode) {
 		this.imgCode = imgCode;
	}

	public String getImgCode() {
 		return imgCode;
	}

	public void setImgKey(String imgKey) {
 		this.imgKey = imgKey;
	}

	public String getImgKey() {
 		return imgKey;
	}

}
