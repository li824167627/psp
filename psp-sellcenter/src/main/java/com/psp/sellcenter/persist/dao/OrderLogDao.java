package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.OrderLogBean;

public interface OrderLogDao {

	int insert(OrderLogBean Orderlog);

	int selectOrderLogsCount(String uid, String key);

	List<OrderLogBean> selectOrderLogs(String uid, String key);

}
