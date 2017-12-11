package com.psp.admin.model;

import java.sql.Timestamp;

/**
 * 园区信息
 **/
public class ParkBean {
	private String pid; // 园区id
	private String name; // 园区
	private String adminId; // 管理员id
	private String contact; // 联系人
	private String admin;// 创建人
	private String phoneNum; // 联系电话
	private String cityCode; // 城市code
	private String province; // 省，直辖市
	private String city; // 市
	private String district; // 区
	private String coordinate; // 坐标经纬度
	private Timestamp createTime; // 创建时间
	private String brief; // 园区简介
	private Integer status;// 状态
	private String areaArray;// 地区数组
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getAreaArray() {
		return areaArray;
	}
	public void setAreaArray(String areaArray) {
		this.areaArray = areaArray;
	}
	
}
