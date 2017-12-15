package com.psp.sellcenter.model;

import java.sql.Timestamp;

/**
 * 客户基本信息
 **/
public class UserBean {
	private String uid; // 客户id
	private String name; // 姓名
	private String phoneNum; // 手机号
	private String companyName; // 公司名称
	private String position; // 职位
	private String sid; // 当前销售
	private String sellerJson; // 当前销售JSON
	private Integer orderNum; // 工单数量
	private Timestamp createTime; // 创建时间
	private Timestamp communityTime; // 最后沟通时间
	private String label; // 客户标签
	private Integer origin; // 状态1：0线上（PC） 1 园区下单 2 线下
	private Integer level; // 客户级别：0尚未定级 1 有效客户 2 无效客户
	private Integer isAllot; // 是否分配：0待分配，1已分配
	private Integer status; // 客户状态，0:全部1:待沟通客户2:已处理客户
	private String aid; // 当前分配人
	private String adminJson; // 管理人员Json
	private Timestamp allotTime; // 分配时间
	private Integer type;
	private SellerBean seller;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSellerJson() {
		return sellerJson;
	}
	public void setSellerJson(String sellerJson) {
		this.sellerJson = sellerJson;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getOrigin() {
		return origin;
	}
	public void setOrigin(Integer origin) {
		this.origin = origin;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getIsAllot() {
		return isAllot;
	}
	public void setIsAllot(Integer isAllot) {
		this.isAllot = isAllot;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAdminJson() {
		return adminJson;
	}
	public void setAdminJson(String adminJson) {
		this.adminJson = adminJson;
	}
	public Timestamp getAllotTime() {
		return allotTime;
	}
	public void setAllotTime(Timestamp allotTime) {
		this.allotTime = allotTime;
	}
	public Timestamp getCommunityTime() {
		return communityTime;
	}
	public void setCommunityTime(Timestamp communityTime) {
		this.communityTime = communityTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public SellerBean getSeller() {
		return seller;
	}
	public void setSeller(SellerBean seller) {
		this.seller = seller;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
