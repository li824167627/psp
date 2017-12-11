package com.psp.admin.model;

/**
 * 服务商服务信息
 **/
public class ProviderServiceBean {
	private String pid; // 服务商pid
	private Integer cid; // 服务分类
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
}
