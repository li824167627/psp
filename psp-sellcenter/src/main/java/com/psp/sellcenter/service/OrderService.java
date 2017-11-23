package com.psp.sellcenter.service;

import com.psp.sellcenter.controller.res.bean.ROrderBean;
import com.psp.sellcenter.controller.res.bean.ROrderLogsBean;
import com.psp.sellcenter.controller.res.bean.RServiceProviderBean;
import com.psp.sellcenter.service.res.PageResult;

public interface OrderService {
	
	/**
	 * 获取工单列表
	 * @param sid
	 * @param page
	 * @param pageSize
	 * @param filteType
	 * @param stype
	 * @param key
	 * @param stage 
	 * @return
	 */
	PageResult<ROrderBean> getOrders(String sid, int page, int pageSize, int filteType, int stype, String key, String uid, int stage);
	
	/**
	 * 新建工单
	 * @param sid
	 * @param pid
	 * @param provider
	 * @param uid
	 * @param label
	 * @param content
	 * @return
	 */
	boolean addOrder(String sid, String pid, String provider, String uid, String label, String content);
	
	/**
	 * 获取服务商列表Json
	 * @return
	 */
	RServiceProviderBean getServiceProviders();
	
	/**
	 * 获取当前销售订单数量
	 * @param sid
	 * @param stage
	 * @return
	 */
	int getOrderNum2Seller(String sid, int stage);
	
	/**
	 * 获取工单详情
	 * @param sid
	 * @param uid
	 * @return
	 */
	ROrderBean getDetail(String sid, String uid);
	
	/**
	 * 获取工单操作日志列表
	 * @param sid
	 * @param oid
	 * @param key
	 * @return
	 */
	PageResult<ROrderLogsBean> getOrderogs(String sid, String oid, String key);

	

}
