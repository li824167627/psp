package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 更新用户名
 **/
public class UpdateAccountNameParam {
	@NotEmpty(message = "更改姓名不能为空")
	private String name; // 姓名

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

}
