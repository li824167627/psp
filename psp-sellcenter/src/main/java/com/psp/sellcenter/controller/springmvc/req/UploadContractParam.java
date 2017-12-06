package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 上传合同
 **/
public class UploadContractParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	private String contractNo; // 合同编号
	@NotEmpty(message = "合同名称不能为空")
	private String name; // 合同名称
	private String signTime; // 合同签订时间
	private String startTime; // 合同开始时间
	private String endTime; // 合同开始时间
	private String partyA; // 合同甲方JSON
	private String partyB; // 合同乙方JSON
	private String contractUrl; // 合同地址
	private String payment; // 付款方式：0一次性 1分期 2其他
	private String paymentWay; // 分期方案
	private String payDesc; // 付款描述
	private String service; // 服务事项
	private Double money; // 合同金额

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

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setSignTime(String signTime) {
 		this.signTime = signTime;
	}

	public String getSignTime() {
 		return signTime;
	}

	public void setStartTime(String startTime) {
 		this.startTime = startTime;
	}

	public String getStartTime() {
 		return startTime;
	}

	public void setEndTime(String endTime) {
 		this.endTime = endTime;
	}

	public String getEndTime() {
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

	public void setContractUrl(String contractUrl) {
 		this.contractUrl = contractUrl;
	}

	public String getContractUrl() {
 		return contractUrl;
	}

	public void setPayment(String payment) {
 		this.payment = payment;
	}

	public String getPayment() {
 		return payment;
	}

	public void setPaymentWay(String paymentWay) {
 		this.paymentWay = paymentWay;
	}

	public String getPaymentWay() {
 		return paymentWay;
	}

	public void setPayDesc(String payDesc) {
 		this.payDesc = payDesc;
	}

	public String getPayDesc() {
 		return payDesc;
	}

	public void setService(String service) {
 		this.service = service;
	}

	public String getService() {
 		return service;
	}

	public void setMoney(Double money) {
 		this.money = money;
	}

	public Double getMoney() {
 		return money;
	}

}
