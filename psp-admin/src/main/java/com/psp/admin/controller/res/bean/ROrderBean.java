package com.psp.admin.controller.res.bean;

import java.util.List;
import java.util.ArrayList;

/**
 * 工单信息
 **/
public class ROrderBean {
	private String oid; // 工单信息id
	private String orderNo; // 工单号
	private String sid; // 当前销售id
	private String uid; // 客户id
	private String userJson; // 客户信息
	private String sellerJson; // 销售信息
	private String label; // 工单标签
	private String content; // 工单需求描述
	private String pid; // 服务商
	private String providerJson; // 服务商信息
	private Long createTime; // 创建时间
	private Integer status; // 状态
	private Integer stage; // 所处阶段
	private Integer isAllot; // 是否被分配
	private Long expectedTime; // 预计完成时间
	private Long completeTime; // 实际完成时间
	private Long closeTime; // 关闭时间
	private Long updateime; // 操作更新时间
	private Long isNeedInvoice; // 是否需要发票
	private List<ROrderContractBean> contracts; // 合同信息
	private ROrderFeedbackBean feedback; // 反馈意见

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setOrderNo(String orderNo) {
 		this.orderNo = orderNo;
	}

	public String getOrderNo() {
 		return orderNo;
	}

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setUserJson(String userJson) {
 		this.userJson = userJson;
	}

	public String getUserJson() {
 		return userJson;
	}

	public void setSellerJson(String sellerJson) {
 		this.sellerJson = sellerJson;
	}

	public String getSellerJson() {
 		return sellerJson;
	}

	public void setLabel(String label) {
 		this.label = label;
	}

	public String getLabel() {
 		return label;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setProviderJson(String providerJson) {
 		this.providerJson = providerJson;
	}

	public String getProviderJson() {
 		return providerJson;
	}

	public void setCreateTime(Long createTime) {
 		this.createTime = createTime;
	}

	public Long getCreateTime() {
 		return createTime;
	}

	public void setStatus(Integer status) {
 		this.status = status;
	}

	public Integer getStatus() {
 		return status;
	}

	public void setStage(Integer stage) {
 		this.stage = stage;
	}

	public Integer getStage() {
 		return stage;
	}

	public void setIsAllot(Integer isAllot) {
 		this.isAllot = isAllot;
	}

	public Integer getIsAllot() {
 		return isAllot;
	}

	public void setExpectedTime(Long expectedTime) {
 		this.expectedTime = expectedTime;
	}

	public Long getExpectedTime() {
 		return expectedTime;
	}

	public void setCompleteTime(Long completeTime) {
 		this.completeTime = completeTime;
	}

	public Long getCompleteTime() {
 		return completeTime;
	}

	public void setCloseTime(Long closeTime) {
 		this.closeTime = closeTime;
	}

	public Long getCloseTime() {
 		return closeTime;
	}

	public void setUpdateime(Long updateime) {
 		this.updateime = updateime;
	}

	public Long getUpdateime() {
 		return updateime;
	}

	public void setIsNeedInvoice(Long isNeedInvoice) {
 		this.isNeedInvoice = isNeedInvoice;
	}

	public Long getIsNeedInvoice() {
 		return isNeedInvoice;
	}

	public void setContracts(List<ROrderContractBean> contracts) {
 		this.contracts = contracts;
	}

	public List<ROrderContractBean> getContracts() {
 		return contracts;
	}

	public void setFeedback(ROrderFeedbackBean feedback) {
 		this.feedback = feedback;
	}

	public ROrderFeedbackBean getFeedback() {
 		return feedback;
	}

}
