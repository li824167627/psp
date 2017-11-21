package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 新建客户
 **/
public class AddUserParam {
	@NotEmpty(message = "姓名不能为空")
	private String name; // 姓名
	@NotEmpty(message = "手机号不能为空！")
	@Pattern(regexp = "^(1[1-9])\\d{9}$", message = "手机号格式不正确！")
	private String phoneNum; // 手机号码
	private String companyName; // 公司名称
	private String position; // 职位
	private String label; // 标签
	@Pattern(regexp = "^0|1$", message = "更新：1:更新 0不更新")
	private String isUpdate; // 更新：1:更新 0不更新
	@Pattern(regexp = "^0|1$", message = "认领：1:认领 0不认领")
	private String isClaim; // 认领：1:认领 0不认领

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

	public void setIsUpdate(String isUpdate) {
 		this.isUpdate = isUpdate;
	}

	public String getIsUpdate() {
 		return isUpdate;
	}

	public void setIsClaim(String isClaim) {
 		this.isClaim = isClaim;
	}

	public String getIsClaim() {
 		return isClaim;
	}

}
