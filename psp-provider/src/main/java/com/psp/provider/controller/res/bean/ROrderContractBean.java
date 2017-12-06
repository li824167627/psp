package com.psp.provider.controller.res.bean;


/**
 * 合同工单详情信息
 **/
public class ROrderContractBean {
	private Integer cid; // cid
	private String name; // 工单名称
	private String oid; // 工单ID
	private String contractNo; // 合同编号
	private String contractUrl; // 合同附件地址
	private Long signTime; // 合同签订时间
	private Long startTime; // 合同开始时间
	private Long endTime; // 合同开始时间
	private String partyA; // 合同甲方JSON
	private String partyB; // 合同乙方JSON
	private Double money; // 金额
	private String service; // 服务事项
	private Integer payment; // 付款方式：0一次性 1分期 2其他
	private String paymentWay; // 分期方案
	private String paymentDesc; // 付款描述

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

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setContractNo(String contractNo) {
 		this.contractNo = contractNo;
	}

	public String getContractNo() {
 		return contractNo;
	}

	public void setContractUrl(String contractUrl) {
 		this.contractUrl = contractUrl;
	}

	public String getContractUrl() {
 		return contractUrl;
	}

	public void setSignTime(Long signTime) {
 		this.signTime = signTime;
	}

	public Long getSignTime() {
 		return signTime;
	}

	public void setStartTime(Long startTime) {
 		this.startTime = startTime;
	}

	public Long getStartTime() {
 		return startTime;
	}

	public void setEndTime(Long endTime) {
 		this.endTime = endTime;
	}

	public Long getEndTime() {
 		return endTime;
	}

	public void setPartyA(String partyA) {
 		this.partyA = partyA;
	}

	public String getPartyA() {
 		return partyA;
	}

	public void setPartyB(String partyB) {
 		this.partyB = partyB;
	}

	public String getPartyB() {
 		return partyB;
	}

	public void setMoney(Double money) {
 		this.money = money;
	}

	public Double getMoney() {
 		return money;
	}

	public void setService(String service) {
 		this.service = service;
	}

	public String getService() {
 		return service;
	}

	public void setPayment(Integer payment) {
 		this.payment = payment;
	}

	public Integer getPayment() {
 		return payment;
	}

	public void setPaymentWay(String paymentWay) {
 		this.paymentWay = paymentWay;
	}

	public String getPaymentWay() {
 		return paymentWay;
	}

	public void setPaymentDesc(String paymentDesc) {
 		this.paymentDesc = paymentDesc;
	}

	public String getPaymentDesc() {
 		return paymentDesc;
	}

}
