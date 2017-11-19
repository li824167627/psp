package com.psp.admin.service.exception;

import com.psp.admin.constants.Rescode;
import com.psp.admin.constants.RescodeConstants;

/**
 * 服务层的异常父类
 * 
 * @author candy
 *
 */
public class ServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int serviceCode;
	private String serviceMsg;

	public ServiceException() {
		super();
	}

	public ServiceException(int serviceCode, String serviceMsg) {
		setServiceCode(serviceCode);
		setServiceMsg(serviceMsg);
	}

	public ServiceException(Rescode res) {
		setServiceCode(res.getCode());
		setServiceMsg(res.getMsg());
	}

	public ServiceException(String key) {
		this(RescodeConstants.getInstance().get(key));
	}

	public ServiceException(String key, Object... args) {
		this(RescodeConstants.getInstance().get(key), args);
	}

	public ServiceException(Rescode res, Object... args) {
		setServiceCode(res.getCode());
		setServiceMsg(res.getMsg(args));
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceMsg() {
		return serviceMsg;
	}

	public void setServiceMsg(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

}
