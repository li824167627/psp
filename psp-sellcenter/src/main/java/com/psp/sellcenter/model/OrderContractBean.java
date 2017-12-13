package com.psp.sellcenter.model;

import java.sql.Timestamp;

/**
 * 工单合同信息
 **/
public class OrderContractBean {

	private Integer cid; // 工单合同id
	private String oid; // 订单ID
	private String contractNo;// 合同编号
	private String partyA;// 甲方JSON
	private String partyB;// 乙方JSON
	private String contractUrl; // 合同附件
	private Timestamp createTime; // 创建时间
	private Timestamp signTime; // 签订时间
	private Timestamp startTime; // 开始时间
	private Timestamp endTime; // 结束时间
	private String service; // 销售id
	private Double money; // 销售信息
	private Integer payment; // 付款方式
	private String paymentWay; // JSON
	private String paymentDesc;
	private String name;// 合同名称
	private Integer type;// 合同类型：1客户合同2服务商合同
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getPartyA() {
		return partyA;
	}
	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}
	public String getPartyB() {
		return partyB;
	}
	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}
	public String getContractUrl() {
		return contractUrl;
	}
	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getSignTime() {
		return signTime;
	}
	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
