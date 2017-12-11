package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.OrderBean;

public interface OrderDao {

	int selectOrderCount(int filteType, int stype, String key, int stage, int ttype, String targetId);

	List<OrderBean> selectOrders(int page, int pageSize, int filteType, int stype, String key, int stage, int ttype,
			String targetId);

	OrderBean selectOrderById(String oid);

	int selectParkOrderNum(String pid);


}
