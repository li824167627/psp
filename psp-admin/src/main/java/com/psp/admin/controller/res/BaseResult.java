package com.psp.admin.controller.res;

import com.psp.admin.constants.Rescode;
import com.psp.admin.constants.RescodeConstants;
import com.psp.admin.service.exception.ServiceException;

/**
 * 返回结果
 * 
 * @author cuihaidong
 *
 */
public class BaseResult {

	public final static Rescode success = RescodeConstants.getInstance().get("success");
	public final static Rescode fail = RescodeConstants.getInstance().get("fail");
	public final static Rescode session = RescodeConstants.getInstance().get("session_fail");
	public final static Rescode param = RescodeConstants.getInstance().get("param_is_error");

	static {

	}

	private int rescode = success.getCode();// 返回值
	private String msg = success.getMsg();// 返回信息
	private String token;// token
	private long sysTime = System.currentTimeMillis() / 1000;// 系统时间 s

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getSysTime() {
		return sysTime;
	}

	public void setSysTime(long sysTime) {
		this.sysTime = sysTime;
	}

	public int getRescode() {
		return rescode;
	}

	public void setRescode(int rescode) {
		this.rescode = rescode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setFlag(boolean flag) {
		this.rescode = flag ? success.getCode() : fail.getCode();
		this.msg = flag ? success.getMsg() : fail.getMsg();
	}

	public void setServiceException(ServiceException e) {
		this.setRescode(e.getServiceCode());
		this.setMsg(e.getServiceMsg());
	}

}
