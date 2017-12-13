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
	 * @return
	 */
	boolean addOrder(String sid, String pid, String provider, String uid, String label);
	
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
	ROrderBean getDetail(String sid, String oid);
	
	/**
	 * 获取工单操作日志列表
	 * @param sid
	 * @param oid
	 * @param key
	 * @return
	 */
	PageResult<ROrderLogsBean> getOrderogs(String sid, String oid, String key);
	
	/**
	 * 分配工单
	 * @param sid
	 * @param pid
	 * @param provider
	 * @return
	 */
	boolean allotOrder(String sid, String pid, String provider);
	
	/**
	 * 关闭工单
	 * @param sid
	 * @param oid
	 * @param content
	 * @param status
	 * @return
	 */
	boolean closeOrder(String sid, String oid, String content, int status);
	
	/**
	 * 上传合同
	 * @param sid
	 * @param oid
	 * @param contractNo
	 * @param name
	 * @param signTime
	 * @param startTime
	 * @param endTime
	 * @param partA
	 * @param partB
	 * @param contractUrl
	 * @param payment
	 * @param paymentWay
	 * @param service
	 * @param money
	 * @param paymentDesc 
	 * @param contractType 
	 * @return
	 */
	boolean uploadContract(String sid, String oid, String contractNo, String name, String signTime, String startTime,
			String endTime, String partA, String partB, String contractUrl, int payment, String paymentWay,
			String service, double money, String paymentDesc, int contractType);
	
	/**
	 * 确认完成
	 * @param sid
	 * @param oid
	 * @param content
	 * @param type
	 * @return
	 */
	boolean confirmOrder(String sid, String oid, String content, int type);
	
	/**
	 * 工单反馈
	 * @param sid
	 * @param oid
	 * @param content
	 * @param score
	 * @return
	 */
	boolean feedback(String sid, String oid, String content, String score);

	

}
