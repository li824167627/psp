package com.psp.web.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录
 **/
public class LoginParam {
	@NotEmpty(message = "手机或邮箱不能为空")
	private String userName; // 手机或邮箱
	@NotEmpty(message = "密码不能为空")
	private String password; // 密码
	private String imgCode; // 图形验证码,输入次数错误超过5次，需输入图形验证码
	private String device; // 设备

	public void setUserName(String userName) {
 		this.userName = userName;
	}

	public String getUserName() {
 		return userName;
	}

	public void setPassword(String password) {
 		this.password = password;
	}

	public String getPassword() {
 		return password;
	}

	public void setImgCode(String imgCode) {
 		this.imgCode = imgCode;
	}

	public String getImgCode() {
 		return imgCode;
	}

	public void setDevice(String device) {
 		this.device = device;
	}

	public String getDevice() {
 		return device;
	}

}
