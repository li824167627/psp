package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取服务商账户列表
 **/
public class GetProviderAccountListParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20
	@NotEmpty(message = "服务商id不能为空")
	private String pid; // 服务商id

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

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

}
