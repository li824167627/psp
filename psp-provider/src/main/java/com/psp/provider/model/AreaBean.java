package com.psp.provider.model;

import java.util.List;

public class AreaBean {
	private String id;
	private String pid;
	private String adcode;
	private String citycode;
	private String name;
	private Integer level;
	private Double centerlat;
	private Double centerlng;

	List<AreaBean> subArea;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getCenterlat() {
		return centerlat;
	}

	public void setCenterlat(Double centerlat) {
		this.centerlat = centerlat;
	}

	public Double getCenterlng() {
		return centerlng;
	}

	public void setCenterlng(Double centerlng) {
		this.centerlng = centerlng;
	}

	public List<AreaBean> getSubArea() {
		return subArea;
	}

	public void setSubArea(List<AreaBean> subArea) {
		this.subArea = subArea;
	}

}
