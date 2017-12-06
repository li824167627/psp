package com.psp.web.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 提交工单
 **/
public class SubmitOrderParam {
	private Integer cid; // 所选服务分类
	@NotEmpty(message = "姓名不能为空")
	private String userName; // 姓名
	@NotEmpty(message = "手机号不能为空")
	private String phoneNum; // 手机号
	private String content; // 需求描述

	public void setCid(Integer cid) {
 		this.cid = cid;
	}

	public Integer getCid() {
 		return cid;
	}

	public void setUserName(String userName) {
 		this.userName = userName;
	}

	public String getUserName() {
 		return userName;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

}
