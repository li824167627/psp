package com.psp.admin.controller.springmvc.req;



/**
 * 删除服务
 **/
public class DelServiceParam {
	private String cid; // 所选分类

	public void setCid(String cid) {
 		this.cid = cid;
	}

	public String getCid() {
 		return cid;
	}

}
