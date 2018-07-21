package com.psp.admin.controller.springmvc.req;


import javax.validation.constraints.Pattern;

/**
 * 获取客户工单列表
 **/
public class GetOrdersParam {
	private Integer page; // 页码，默认从0开始
	private Integer pagesize; // 每页数量，默认20
	@Pattern(regexp = "^0|1$", message = "搜索阶段错误：0:全部1:待处理")
	private String stage; // 阶段：0 全部： 1待处理
	@Pattern(regexp = "^0|1|2|3$", message = "所属人条件错误：0:全部1:服务商2:客户3:销售")
	private String ttype; // 所属人，0:全部1:服务商2:客户3:销售
	private String targetId; // 客户ID，空则查询当前销售待工单
	@Pattern(regexp = "^0|1|2|3$", message = "搜索条件错误：0:全部1:描述2:标签3:销售姓名")
	private String stype; // 搜索条件类型，0:全部1:描述2:标签3销售姓名
	private String key; // 关键字
	@Pattern(regexp = "^[0-9]|10|11|99$", message = "搜索条件错误： 99全部0:待分配1已完成2:待处理3:已接受4:合同一上传5:申请完成6:待反馈7拒绝完成8申请终止")
	private String filteType; // 筛选工单 0全部1:待分配2:待处理3:已接受4:合同一上传5:申请完成6:待反馈7拒绝完成8申请终止
	private String dataType; // 类型 1：真实  99:全部

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

	public void setStage(String stage) {
 		this.stage = stage;
	}

	public String getStage() {
 		return stage;
	}

	public void setTtype(String ttype) {
 		this.ttype = ttype;
	}

	public String getTtype() {
 		return ttype;
	}

	public void setTargetId(String targetId) {
 		this.targetId = targetId;
	}

	public String getTargetId() {
 		return targetId;
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

	public void setDataType(String dataType) {
 		this.dataType = dataType;
	}

	public String getDataType() {
 		return dataType;
	}

}
