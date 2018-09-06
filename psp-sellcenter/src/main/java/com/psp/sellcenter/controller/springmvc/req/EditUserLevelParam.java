package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 编辑客户级别
 **/
public class EditUserLevelParam {
	@NotEmpty(message = "客户id不能为空")
	private String userId; // 客户id
	@Pattern(regexp = "^0|1|2$", message = "客户级别错误")
	private String level; // 客户级别 0尚未定级 1 有效客户 2 无效客户

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

}
