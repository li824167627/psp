package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑客户标签
 **/
public class EditUserLabelParam {
	@NotEmpty(message = "客户id不能为空")
	private String userId; // 客户id
	private String label; // 客户标签

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
