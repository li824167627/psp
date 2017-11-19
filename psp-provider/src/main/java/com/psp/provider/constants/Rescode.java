package com.psp.provider.constants;

/**
 * 返回结果常量
 */
public class Rescode {

	private String name;
	private int code;
	private String msg;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public String getMsg(Object... args) {
		return msg == null ? null : String.format(msg, args);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
