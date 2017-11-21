package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 新建/编辑客户消息
 **/
public class EditUserNewsParam {
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户ID
	private String nid; // 消息流id，空时为新建
	private String label; // 信息标签
	@NotEmpty(message = "描述不能为空")
	private String content; // 描述

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setNid(String nid) {
 		this.nid = nid;
	}

	public String getNid() {
 		return nid;
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
