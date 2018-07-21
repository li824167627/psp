package com.psp.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.psp.admin.controller.res.bean.ROrderBean;
import com.psp.admin.controller.res.bean.ROrderLogsBean;
import com.psp.admin.service.res.PageResult;

public interface OrderService {

	/**
	 * 获取工单列表
	 * 
	 * @param adminId
	 * @param page
	 * @param pageSize
	 * @param filteType
	 * @param stype
	 * @param key
	 * @param targetId
	 * @param ttype
	 * @param stage
	 * @return
	 */
	PageResult<ROrderBean> getOrders(String adminId, int page, int pageSize, int filteType, int stype, String key,
			String targetId, int ttype, int stage, int saleType,int dataType);

	/**
	 * 获取各个阶段工单数量
	 * 
	 * @param adminId
	 * @param stage
	 * @return
	 */
	int getOrderNum(String adminId, int stage);

	/**
	 * 获取工单详情
	 * 
	 * @param adminId
	 * @param oid
	 * @return
	 */
	ROrderBean getDetail(String adminId, String oid);

	/**
	 * 获取工单操作日志
	 * 
	 * @param sid
	 * @param oid
	 * @param key
	 * @return
	 */
	PageResult<ROrderLogsBean> getOrderLogs(String sid, String oid, String key);

	/**
	 * 导入excel
	 * 
	 * @param request
	 * @return
	 */
	boolean ImportOrders(HttpServletRequest request) throws Exception;

}
