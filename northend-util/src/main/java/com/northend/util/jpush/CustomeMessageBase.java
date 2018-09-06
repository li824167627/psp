package com.northend.util.jpush;

/**
 * 推送系统消息基本类型
 * 
 * @param <T>
 */
public class CustomeMessageBase<T> {

	private String mid; // 消息eid
	private String title; // 消息标题
	private String subTitle; // 消息内容
	private int type; // 消息类型
	private T data; // 额外的消息 这里是map<String,String>
	private String phone; // 客服电话
	private long createTime; // 推送时间 秒的时间戳

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
