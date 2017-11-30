package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 获取客户信息流
 **/
public class GetUserNewsParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户ID
	@Pattern(regexp = "^0|1|2$", message = "搜索条件错误：0:全部1:描述2:标签")
	private String stype; // 搜索条件类型，0:全部1:描述2:标签
	private String key; // 关键字

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

}
