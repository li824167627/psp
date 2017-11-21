package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 编辑客户
 **/
public class EditUserParam {
	@NotEmpty(message = "客户id不能为空")
	private String userId; // 客户id
	private String name; // 姓名
	@Pattern(regexp = "^(1[1-9])\\d{9}$", message = "手机号格式不正确！")
	private String phoneNum; // 手机号码
	private String companyName; // 公司名称
	private String position; // 职位
	private String label; // 标签

	public void setUserId(String userId) {
 		this.userId = userId;
	}

	public String getUserId() {
 		return userId;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setCompanyName(String companyName) {
 		this.companyName = companyName;
	}

	public String getCompanyName() {
 		return companyName;
	}

	public void setPosition(String position) {
 		this.position = position;
	}

	public String getPosition() {
 		return position;
	}

	public void setLabel(String label) {
 		this.label = label;
	}

	public String getLabel() {
 		return label;
	}

}
