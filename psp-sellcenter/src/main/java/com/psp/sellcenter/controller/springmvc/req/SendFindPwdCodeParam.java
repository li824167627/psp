package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 找回密码验证手机
 **/
public class SendFindPwdCodeParam {
	@NotEmpty(message = "账号不能为空")
	private String account; // 手机或邮箱
	private String imgKey; // 获取时返回的key
	@NotEmpty(message = "图形验证码不能为空")
	private String imgCode; // 图形验证码

	public void setAccount(String account) {
 		this.account = account;
	}

	public String getAccount() {
 		return account;
	}

	public void setImgKey(String imgKey) {
 		this.imgKey = imgKey;
	}

	public String getImgKey() {
 		return imgKey;
	}

	public void setImgCode(String imgCode) {
 		this.imgCode = imgCode;
	}

	public String getImgCode() {
 		return imgCode;
	}

}
