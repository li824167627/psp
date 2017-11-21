package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 分配客户给销售
 **/
public class AllotParam {
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户id
	@NotEmpty(message = "销售id不能为空")
	private String sid; // 销售id

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

}
