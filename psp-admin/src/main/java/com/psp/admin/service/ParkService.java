package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RParkBean;
import com.psp.admin.service.res.PageResult;

public interface ParkService {
	
	/**
	 * 获取园区列表
	 * @param adminId
	 * @param page
	 * @param pageSize
	 * @param key
	 * @return
	 */
	PageResult<RParkBean> getList(String adminId, int page, int pageSize, String key);
	
	/**
	 * 新建编辑园区
	 * @param adminId
	 * @param name
	 * @param pid
	 * @param contact
	 * @param phoneNum
	 * @param cityCode
	 * @param province
	 * @param city
	 * @param district
	 * @param coordinate
	 * @param brief
	 * @param areaArr 
	 * @return
	 */
	boolean eidtPark(String adminId, String name, String pid, String contact, String phoneNum, String cityCode,
			String province, String city, String district, String coordinate, String brief, String areaArr);
	
	/**
	 * 删除园区
	 * @param adminId
	 * @param pid
	 * @return
	 */
	boolean del(String adminId, String pid);
	
	/**
	 * 
	 * @param adminId
	 * @param pid
	 * @return
	 */
	int getUserNum(String adminId, String pid);

	int getOrderNum(String adminId, String pid);
	

}
