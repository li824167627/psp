package com.psp.web.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取服务列表
 **/
public class GetServiceParam {
	@NotEmpty(message = "请选择服务分类")
	private String parentId; // 服务所属的二级服务分类

	public void setParentId(String parentId) {
 		this.parentId = parentId;
	}

	public String getParentId() {
 		return parentId;
	}

}
