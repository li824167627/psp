package com.psp.admin.controller.springmvc.req;


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
	private String isAllot; // 搜索客户状态，0:待分配1:已分配客户
	private String sid; // 销售ID

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

	public void setIsAllot(String isAllot) {
 		this.isAllot = isAllot;
	}

	public String getIsAllot() {
 		return isAllot;
	}

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

}
