package com.psp.web.controller.res.bean;


/**
 * 用户信息
 **/
public class RUserBean {
	private String uid; // 用户的uid
	private String phoneNum; // 用户手机号
	private String nickName; // 用户昵称
	private Integer status; // 状态1：正常，0：禁用
	private Integer type; // 用户类型 0：正常，1：导入
	private Long regTime; // 注册时间

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setNickName(String nickName) {
 		this.nickName = nickName;
	}

	public String getNickName() {
 		return nickName;
	}

	public void setStatus(Integer status) {
 		this.status = status;
	}

	public Integer getStatus() {
 		return status;
	}

	public void setType(Integer type) {
 		this.type = type;
	}

	public Integer getType() {
 		return type;
	}

	public void setRegTime(Long regTime) {
 		this.regTime = regTime;
	}

	public Long getRegTime() {
 		return regTime;
	}

}
