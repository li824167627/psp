package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑服务商
 **/
public class EditProviderParam {
	@NotEmpty(message = "设置服务商不能为空")
	private String pid; // 服务商id
	@NotEmpty(message = "名称不能为空")
	private String name; // 服务商名称
	private String address; // 服务商地址
	@NotEmpty(message = "联系人不能为空")
	private String contact; // 联系人，根据联系人和手机号生成服务商管理账号
	@NotEmpty(message = "手机号不能为空")
	private String phoneNum; // 手机号，根据联系人和手机号生成服务商管理账号
	private String content; // 服务内容

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setAddress(String address) {
 		this.address = address;
	}

	public String getAddress() {
 		return address;
	}

	public void setContact(String contact) {
 		this.contact = contact;
	}

	public String getContact() {
 		return contact;
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
