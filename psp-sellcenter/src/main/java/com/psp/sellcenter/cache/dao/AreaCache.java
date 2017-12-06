package com.psp.sellcenter.cache.dao;

import java.util.List;

public interface AreaCache {

	/**
	 * 获取区域省、市、区
	 * 
	 * @return
	 */
	String getAreaPCD();

	/**
	 * 获取区域省、市、区
	 * 
	 * @param string
	 */
	boolean setAreaPCD(String string);

	/**
	 * 通过id 获取下级区域
	 * 
	 * @param id
	 * @return
	 */
	String getSubAreaById(String id);

	/**
	 * 通过id 设置下级区域
	 * 
	 * @param id
	 * @param string
	 * @return
	 */
	boolean setSubAreaById(String id, String string);

	/**
	 * 获取省市区
	 * 
	 * @param pcdsArray
	 * @return
	 */
	List<String> getPCDSList(String p, String c, String d);

}
