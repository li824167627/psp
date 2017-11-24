package com.psp.provider.service;

import com.psp.provider.controller.res.bean.ROrderBean;
import com.psp.provider.controller.res.bean.ROrderLogsBean;
import com.psp.provider.model.AccountBean;
import com.psp.provider.service.impl.res.PageResult;

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
	PageResult<ROrderBean> getOrders(AccountBean account, int page, int pageSize, int filteType, int stype, String key,
			int stage);
	
	/**
	 * 获取各个阶段工单数量
	 * @param account
	 * @param stage
	 * @return
	 */
	int getOrderNum2Provider(AccountBean account, int stage);
	
	/**
	 * 获取工单详情
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	ROrderBean getDetail(AccountBean account, String oid);
	
	/**
	 * 获取操作工单日志列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	PageResult<ROrderLogsBean> getOrderogs(AccountBean account, String oid, String key);
	
	/**
	 * 申请完成工单
	 * @param oid
	 * @param type
	 * @param content
	 * @param account
	 * @return
	 */
	boolean submitFinish(String oid, int type, String content, AccountBean account);
	
	/**
	 * 拒绝工单
	 * @param oid
	 * @param content
	 * @param account
	 * @return
	 */
	boolean refuseOrder(String oid, String content, AccountBean account);
	
	/**
	 * 接收工单
	 * @param oid
	 * @param content
	 * @param account
	 * @return
	 */
	boolean acceptOrder(String oid, String content, AccountBean account);
	
	/**
	 * 申请终止工单
	 * @param oid
	 * @param type
	 * @param content
	 * @param account
	 * @return
	 */
	boolean submitClose(String oid, int type, String content, AccountBean account);

}