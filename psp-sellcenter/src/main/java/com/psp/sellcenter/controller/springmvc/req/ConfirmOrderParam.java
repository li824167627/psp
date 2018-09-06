package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 确认工单
 **/
public class ConfirmOrderParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	@Pattern(regexp = "^1|2$", message = "完成情况错误：1完成工单2拒绝完成")
	private String type; // 工单完成情况确认：1完成工单2拒绝完成
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
