package com.psp.web.controller.res.bean;


/**
 * 服务分类信息
 **/
public class RCategoryBean {
	private Integer cid; // 操作id
	private String name; // 服务分类名称
	private Integer parentId; // 父节点
	private Integer sort; // 排序
	private Long createTime; // 创建时间
	private String adminId; // 创建管理员
	private String adminJson; // 创建管理员

	public void setCid(Integer cid) {
 		this.cid = cid;
	}

	public Integer getCid() {
 		return cid;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setParentId(Integer parentId) {
 		this.parentId = parentId;
	}

	public Integer getParentId() {
 		return parentId;
	}

	public void setSort(Integer sort) {
 		this.sort = sort;
	}

	public Integer getSort() {
 		return sort;
	}

	public void setCreateTime(Long createTime) {
 		this.createTime = createTime;
	}

	public Long getCreateTime() {
 		return createTime;
	}

	public void setAdminId(String adminId) {
 		this.adminId = adminId;
	}

	public String getAdminId() {
 		return adminId;
	}

	public void setAdminJson(String adminJson) {
 		this.adminJson = adminJson;
	}

	public String getAdminJson() {
 		return adminJson;
	}

}
