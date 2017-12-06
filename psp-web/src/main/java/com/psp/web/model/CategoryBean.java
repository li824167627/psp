package com.psp.web.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * 分类
 * @author chengl
 *
 */
public class CategoryBean {
	private Integer cid;
	private String name;
	private Integer parentId;
	private Integer sort;
	private Timestamp createTime;
	private String adminId;
	private Integer isService;
	
	private List<CategoryBean> childern;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public List<CategoryBean> getChildern() {
		return childern;
	}
	public void setChildern(List<CategoryBean> childern) {
		this.childern = childern;
	}
	public Integer getIsService() {
		return isService;
	}
	public void setIsService(Integer isService) {
		this.isService = isService;
	}
	
}
