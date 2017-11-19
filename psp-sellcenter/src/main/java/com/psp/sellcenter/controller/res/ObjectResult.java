package com.psp.sellcenter.controller.res;

/**
 * 对象返回结果
 * 
 * @author cuihaidong
 *
 */
public class ObjectResult<T> extends BaseResult {

	private T data;// 对象数据
	
	private int errorCount;// 错误次数
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}


}
