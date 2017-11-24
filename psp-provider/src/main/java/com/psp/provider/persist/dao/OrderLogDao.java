package com.psp.provider.persist.dao;

import java.util.List;

import com.psp.provider.model.OrderLogBean;

public interface OrderLogDao {

	int insert(OrderLogBean Orderlog);

	int selectOrderLogsCount(String uid, String key);

	List<OrderLogBean> selectOrderLogs(String uid, String key);
	

}
