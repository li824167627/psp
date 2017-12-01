package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑园区
 **/
public class EditParkParam {
	private String pid; // 园区id
	@NotEmpty(message = "名称不能为空")
	private String name; // 园区名称
	private String contact; // 联系人
	@NotEmpty(message = "手机号不能为空")
	private String phoneNum; // 手机号
	private String cityCode; // 城市编号
	private String province; // 省(直辖市)
	private String city; // 市
	private String district; // 区
	private String coordinate; // 坐标
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

	public void setBrief(String brief) {
 		this.brief = brief;
	}

	public String getBrief() {
 		return brief;
	}

}
