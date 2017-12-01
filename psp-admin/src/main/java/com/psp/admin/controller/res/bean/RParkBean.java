package com.psp.admin.controller.res.bean;


/**
 * 园区信息
 **/
public class RParkBean {
	private String pid; // 园区id
	private String name; // 园区
	private String adminId; // 管理员id
	private String contact; // 联系人
	private String phoneNum; // 联系电话
	private String cityCode; // 城市code
	private String province; // 省，直辖市
	private String city; // 市
	private String district; // 区
	private String coordinate; // 坐标经纬度
	private Long createTime; // 创建时间
	private Integer status; // 状态
	private String brief; // 园区简介

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

	public void setAdminId(String adminId) {
 		this.adminId = adminId;
	}

	public String getAdminId() {
 		return adminId;
	}

	public void setContact(String contact) {
 		this.contact = contact;
	}

	public String getContact() {
 		return contact;
	}

	public void setPhoneNum(String phoneNum) {
 		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
 		return phoneNum;
	}

	public void setCityCode(String cityCode) {
 		this.cityCode = cityCode;
	}

	public String getCityCode() {
 		return cityCode;
	}

	public void setProvince(String province) {
 		this.province = province;
	}

	public String getProvince() {
 		return province;
	}

	public void setCity(String city) {
 		this.city = city;
	}

	public String getCity() {
 		return city;
	}

	public void setDistrict(String district) {
 		this.district = district;
	}

	public String getDistrict() {
 		return district;
	}

	public void setCoordinate(String coordinate) {
 		this.coordinate = coordinate;
	}

	public String getCoordinate() {
 		return coordinate;
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

	public void setBrief(String brief) {
 		this.brief = brief;
	}

	public String getBrief() {
 		return brief;
	}

}
