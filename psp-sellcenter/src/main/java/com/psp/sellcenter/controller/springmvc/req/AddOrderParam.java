package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 新建客户工单
 **/
public class AddOrderParam {
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户ID
	@NotEmpty(message = "服务商不能为空")
	private String pid; // 选择服务商
	private String label; // 工单标签
	@NotEmpty(message = "描述不能为空")
	private String content; // 描述

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
