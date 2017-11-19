package com.psp.sellcenter.controller.res;

import java.util.List;

/**
 * 列表
 * 
 * @author cuihaidong
 *
 */
public class ListResult<T> extends BaseResult {

	private List<T> data;// 列表数据

	private int page;// 当前请求页
	private int totalSize;// 返回总数量
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
