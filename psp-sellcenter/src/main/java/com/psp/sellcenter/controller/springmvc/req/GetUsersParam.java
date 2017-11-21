package com.psp.sellcenter.controller.springmvc.req;


import javax.validation.constraints.Pattern;

/**
 * 获取客户列表
 **/
public class GetUsersParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20
	@Pattern(regexp = "^0|1|2|3|4$", message = "搜索条件错误：0:全部1:姓名2:手机号3:公司名称4:标签")
	private String stype; // 搜索条件类型，0:全部1:姓名2:手机号3:公司名称4:标签
	private String key; // 关键字
	@Pattern(regexp = "^0|1|2|3$", message = "搜索条件错误：0全部1:有效2:无效3:待定级")
	private String filteType; // 筛选客户等级，0全部1:有效2:无效3:待定级
	@Pattern(regexp = "^0|1|2$", message = "状态错误：0:全部1:待沟通客户2:已处理客户")
	private String status; // 搜索客户状态，0:全部1:待沟通客户2:已处理客户

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

	public void setStatus(String status) {
 		this.status = status;
	}

	public String getStatus() {
 		return status;
	}

}
