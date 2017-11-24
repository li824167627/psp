package com.psp.sellcenter.persist.dao;

import java.util.List;

import com.psp.sellcenter.model.OrderBean;
import com.psp.sellcenter.model.OrderContractBean;

public interface OrderDao {
	
	int selectOrderCount(String sid, int filteType, int stype, String key, String uid, int stage);

	List<OrderBean> selectOrders(int page, int pageSize, String sid, int filteType, int stype, String key, String uid, int stage);

	int insert(OrderBean order);

	int selectOrderCount2Seller(String sid, int stage);

	OrderBean selectOrderById(String oid);

	int updateProvider(OrderBean order);

	int updateStatus(OrderBean order);

	int insertContract(OrderContractBean contract);


}