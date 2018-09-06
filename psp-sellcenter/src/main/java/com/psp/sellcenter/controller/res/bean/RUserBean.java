package com.psp.sellcenter.controller.res.bean;

/**
 * 客户基本信息
 **/
public class RUserBean {
	private String uid; // 客户id
	private String name; // 姓名
	private String phoneNum; // 手机号
	private String companyName; // 公司名称
	private String position; // 职位
	private String visitDest; // 参观目的
	private String visitNum; // 参观人数
	private String refCompany; // 推荐单位
	private String referrer; // 推荐人
	private Integer ctype; // 客户类型：1 普通 2 参观
	private Long visitTime; // 访问时间
	private String escort; // 职位
	private String introducer; // 引导介绍
	private String remark; // 备注
	private String visitflow; // 参观流程
	private String sid; // 当前销售
	private String createrJson; // 创建人
	private String sellerJson; // 当前销售JSON
	private Integer orderNum; // 工单数量
	private Long createTime; // 创建时间
	private String label; // 客户标签
	private Integer origin; // 状态1：0线上（PC） 1 园区下单 2 线下
	private Integer level; // 客户级别：0尚未定级 1 有效客户 2 无效客户
	private Integer isAllot; // 是否分配：0待分配，1已分配
	private Integer status; // 客户状态，0:全部1:待沟通客户2:已处理客户
	private String aid; // 当前分配人
	private String adminJson; // 管理人员Json
	private Long allotTime; // 分配时间

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setVisitDest(String visitDest) {
		this.visitDest = visitDest;
	}

	public String getVisitDest() {
		return visitDest;
	}

	public void setVisitNum(String visitNum) {
		this.visitNum = visitNum;
	}

	public String getVisitNum() {
		return visitNum;
	}

	public void setRefCompany(String refCompany) {
		this.refCompany = refCompany;
	}

	public String getRefCompany() {
		return refCompany;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setVisitTime(Long visitTime) {
		this.visitTime = visitTime;
	}

	public Long getVisitTime() {
		return visitTime;
	}

	public void setEscort(String escort) {
		this.escort = escort;
	}

	public String getEscort() {
		return escort;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setVisitflow(String visitflow) {
		this.visitflow = visitflow;
	}

	public String getVisitflow() {
		return visitflow;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return sid;
	}

	public void setCreaterJson(String createrJson) {
		this.createrJson = createrJson;
	}

	public String getCreaterJson() {
		return createrJson;
	}

	public void setSellerJson(String sellerJson) {
		this.sellerJson = sellerJson;
	}

	public String getSellerJson() {
		return sellerJson;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setIsAllot(Integer isAllot) {
		this.isAllot = isAllot;
	}

	public Integer getIsAllot() {
		return isAllot;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAid() {
		return aid;
	}

	public void setAdminJson(String adminJson) {
		this.adminJson = adminJson;
	}

	public String getAdminJson() {
		return adminJson;
	}

	public void setAllotTime(Long allotTime) {
		this.allotTime = allotTime;
	}

	public Long getAllotTime() {
		return allotTime;
	}

}
