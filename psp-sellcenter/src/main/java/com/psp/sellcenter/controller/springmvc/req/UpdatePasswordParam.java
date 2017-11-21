package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 更改密码
 **/
public class UpdatePasswordParam {
	@NotEmpty(message = "历史密码不能为空")
	private String oldPwd; // 历史密码
	@NotEmpty(message = "新密码不能为空")
	private String newPwd; // 新密码
	@NotEmpty(message = "确认密码不能为空")
	private String submitPwd; // 确认密码

	public void setOldPwd(String oldPwd) {
 		this.oldPwd = oldPwd;
	}

	public String getOldPwd() {
 		return oldPwd;
	}

	public void setNewPwd(String newPwd) {
 		this.newPwd = newPwd;
	}

	public String getNewPwd() {
 		return newPwd;
	}

	public void setSubmitPwd(String submitPwd) {
 		this.submitPwd = submitPwd;
	}

	public String getSubmitPwd() {
 		return submitPwd;
	}

}
