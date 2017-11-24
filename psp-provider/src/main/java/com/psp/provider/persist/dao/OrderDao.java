package com.psp.provider.persist.dao;

import java.util.List;

import com.psp.provider.model.OrderBean;

public interface OrderDao {
	
	int selectOrderCount(String sid, int filteType, int stype, String key, String uid, int stage);

	List<OrderBean> selectOrders(int page, int pageSize, String sid, int filteType, int stype, String key, String uid, int stage);

	int insert(OrderBean order);

	int selectOrderCount2Seller(String sid, int stage);

	OrderBean selectOrderById(String oid);

	int updateProvider(OrderBean order);

	int updateStatus(OrderBean order);



}
