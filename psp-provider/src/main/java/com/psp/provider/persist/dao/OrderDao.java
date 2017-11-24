package com.psp.provider.persist.dao;

import java.util.List;

import com.psp.provider.model.OrderBean;

public interface OrderDao {
	
	int selectOrderCount(String sid, int filteType, int stype, String key, int stage);

	List<OrderBean> selectOrders(int page, int pageSize, String sid, int filteType, int stype, String key, int stage);

	int selectOrderCount2Provider(String pid, int stage);

	OrderBean selectOrderById(String oid);

	int updateStatus(OrderBean order);

	int refuseOrder(OrderBean order);



}
