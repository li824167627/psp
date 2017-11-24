package com.psp.admin.controller.springmvc.req;



/**
 * 获取管理员信息
 **/
public class GetProviderListParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20
	private String cid; // 服务分类

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

	public void setCid(String cid) {
 		this.cid = cid;
	}

	public String getCid() {
 		return cid;
	}

}
