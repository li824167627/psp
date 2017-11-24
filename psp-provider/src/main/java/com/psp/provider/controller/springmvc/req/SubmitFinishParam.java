package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 申请完成工单
 **/
public class SubmitFinishParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	@Pattern(regexp = "^1|2|3$", message = "状态错误:1 按期完成，2 延期完成，3其他")
	private String type; // 申请完成状态:1 按期完成，2 延期完成，3其他
	private String content; // 完成描述

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setType(String type) {
 		this.type = type;
	}

	public String getType() {
 		return type;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

}
