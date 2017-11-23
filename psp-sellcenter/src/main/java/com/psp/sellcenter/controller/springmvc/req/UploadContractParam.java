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
	private Long signTime; // 合同签订时间
	private Long startTime; // 合同开始时间
	private Long endTime; // 合同开始时间
	private String partyA; // 合同甲方JSON
	private String partyB; // 合同乙方JSON
	private String contarctUrl; // 合同地址
	private String payment; // 付款方式：0一次性 1分期
	private String deadline; // 分期方案
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

	public void setContarctUrl(String contarctUrl) {
 		this.contarctUrl = contarctUrl;
	}

	public String getContarctUrl() {
 		return contarctUrl;
	}

	public void setPayment(String payment) {
 		this.payment = payment;
	}

	public String getPayment() {
 		return payment;
	}

	public void setDeadline(String deadline) {
 		this.deadline = deadline;
	}

	public String getDeadline() {
 		return deadline;
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
