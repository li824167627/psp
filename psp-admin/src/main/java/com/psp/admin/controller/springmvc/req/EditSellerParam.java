package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 编辑销售
 **/
public class EditSellerParam {
	private String sid; // 销售id
	@NotEmpty(message = "名称不能为空")
	private String name; // 销售名称
	@NotEmpty(message = "选择所属园区")
	private String pid; // 园区id
	@Pattern(regexp = "^0|1$", message = "类型错误：0 园区管理 1 总管理")
	private String type; // 类型
	private String password; // 密码
	@NotEmpty(message = "手机号不能为空")
	private String phoneNum; // 手机号

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setType(String type) {
 		this.type = type;
	}

	public String getType() {
 		return type;
	}

	public void setPassword(String password) {
 		this.password = password;
	}

	public String getPassword() {
 		return password;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

}
