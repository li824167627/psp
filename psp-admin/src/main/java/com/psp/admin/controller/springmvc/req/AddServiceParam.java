package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 创建服务分类
 **/
public class AddServiceParam {
	@NotEmpty(message = "请选择服务分类")
	private String parentId; // 服务所属的二级服务分类
	@Pattern(regexp = "^0|1$", message = "服务分类类型错误：0:服务分类 1:服务")
	private String isService; // 是否是服务：0:服务分类 1:服务
	private String name; // 服务名称
	private Integer sort; // 顺序

	public void setParentId(String parentId) {
 		this.parentId = parentId;
	}

	public String getParentId() {
 		return parentId;
	}

	public void setIsService(String isService) {
 		this.isService = isService;
	}

	public String getIsService() {
 		return isService;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setSort(Integer sort) {
 		this.sort = sort;
	}

	public Integer getSort() {
 		return sort;
	}

}
