package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录
 **/
public class LoginParam {
	@NotEmpty(message = "手机号不能为空")
	private String phoneNum; // 手机号
	@NotEmpty(message = "密码不能为空")
	private String password; // 密码
	private String imgCode; // 图形验证码,输入次数错误超过5次，需输入图形验证码
	private String device; // 设备

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
