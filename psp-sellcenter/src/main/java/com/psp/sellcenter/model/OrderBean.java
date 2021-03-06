package com.psp.sellcenter.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * 工单信息
 **/
public class OrderBean {
	private String oid; // 工单信息id
	private String orderNo; // 工单号
	private String sid; // 当前销售id
	private String sellerJson;
	private String uid; // 客户id
	private String userJson; // 客户信息
	private String label; // 工单标签
	private String content; // 工单需求描述
	private String pid; // 服务商
	private String providerJson; // 服务商信息
	private Timestamp createTime; // 创建时间
	private Double money; // 金额
	private Integer status; // 状态
	private Integer contractStatus; // 状态
	private Integer stage; // 所处阶段
	private Integer isAllot; // 是否被分配
	private Timestamp expectedTime; // 预计完成时间
	private Timestamp completeTime; // 实际完成时间
	private Timestamp closeTime; // 关闭时间
	private Timestamp updateTime; // 操作更新
	private Long isNeedInvoice; // 是否需要发票
	private ProviderBean provider;// 服务商信息
	private List<OrderContractBean> contracts; // 合同信息
	private OrderFeedbackBean feedback;// 反馈
	private UserBean user;
	private Integer dataType;// 0 测试 1 正式 2 补充

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserJson() {
		return userJson;
	}

	public void setUserJson(String userJson) {
		this.userJson = userJson;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProviderJson() {
		return providerJson;
	}

	public void setProviderJson(String providerJson) {
		this.providerJson = providerJson;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Integer getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(Integer isAllot) {
		this.isAllot = isAllot;
	}

	public Timestamp getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(Timestamp expectedTime) {
		this.expectedTime = expectedTime;
	}

	public Timestamp getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

	public Long getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public void setIsNeedInvoice(Long isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public String getSellerJson() {
		return sellerJson;
	}

	public void setSellerJson(String sellerJson) {
		this.sellerJson = sellerJson;
	}

	public ProviderBean getProvider() {
		return provider;
	}

	public void setProvider(ProviderBean provider) {
		this.provider = provider;
	}

	public Timestamp getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public List<OrderContractBean> getContracts() {
		return contracts;
	}

	public void setContracts(List<OrderContractBean> contracts) {
		this.contracts = contracts;
	}

	public OrderFeedbackBean getFeedback() {
		return feedback;
	}

	public void setFeedback(OrderFeedbackBean feedback) {
		this.feedback = feedback;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Integer getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
