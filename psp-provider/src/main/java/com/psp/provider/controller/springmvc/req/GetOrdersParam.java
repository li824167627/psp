package com.psp.provider.controller.springmvc.req;


import javax.validation.constraints.Pattern;

/**
 * 获取工单列表
 **/
public class GetOrdersParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20
	@Pattern(regexp = "^0|1$", message = "搜索阶段错误：0:全部1:待处理")
	private String stage; // 阶段：0 全部： 1待处理
	@Pattern(regexp = "^0|1|2$", message = "搜索条件错误：0:全部1:描述2:标签")
	private String stype; // 搜索条件类型，0:全部1:描述2:标签
	private String key; // 关键字
	@Pattern(regexp = "^[0-9]|10|11|99$", message = "搜索条件错误： 99全部0:待分配1已完成2:待处理3:已接受4:合同一上传5:申请完成6:待反馈7拒绝完成8申请终止")
	private String filteType; // 筛选工单 0全部1:待分配2:待处理3:已接受4:合同一上传5:申请完成6:待反馈7拒绝完成8申请终止

	public void setPage(Integer page) {
 		this.page = page;
	}

	public Integer getPage() {
 		return page;
	}

	public void setPagesize(Integer pagesize) {
 		this.pagesize = pagesize;
	}

	public Integer getPagesize() {
 		return pagesize;
	}

	public void setStage(String stage) {
 		this.stage = stage;
	}

	public String getStage() {
 		return stage;
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
