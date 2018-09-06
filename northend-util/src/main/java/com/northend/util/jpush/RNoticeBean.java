package com.northend.util.jpush;

public class RNoticeBean {

	private String faid; // 添加记录id
	private Integer addType; // 添加好友，1我主动添加，2我被动添加
	private Integer state; // 是否同意：0未操作 ,1同意 ,2不同意
	private String name; // 姓名
	private String letter; // 字符串的第一个字符的首字母
	private String icon; // 用户头像

	public String getFaid() {
		return faid;
	}

	public void setFaid(String faid) {
		this.faid = faid;
	}

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
