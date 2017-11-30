package com.psp.admin.controller.springmvc.req;



/**
 * 获取销售人员列表
 **/
public class GetSellersParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20

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

}
