package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 获取客户工单列表
 **/
public class GetUserOrdersParam {
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户ID
	@Pattern(regexp = "^0|1|2$", message = "搜索条件错误：0:全部1:描述2:标签")
	private String stype; // 搜索条件类型，0:全部1:描述2:标签
	private String key; // 关键字
	@Pattern(regexp = "^[0-8]$", message = "搜索条件错误： 0全部1:待分配2:待处理3:已接受4:合同一上传5:申请完成6:待反馈7拒绝完成8申请终止")
	private String filteType; // 筛选工单 0全部1:待分配2:待处理3:已接受4:合同一上传5:申请完成6:待反馈7拒绝完成8申请终止

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setStype(String stype) {
 		this.stype = stype;
	}

	public String getStype() {
 		return stype;
	}

	public void setKey(String key) {
 		this.key = key;
	}

	public String getKey() {
 		return key;
	}

	public void setFilteType(String filteType) {
 		this.filteType = filteType;
	}

	public String getFilteType() {
 		return filteType;
	}

}
