package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.OrderBean;
import com.psp.admin.model.OrderStageStatisticsBean;
import com.psp.admin.model.OrderStatusStatisticsBean;

public interface OrderDao {

	int selectOrderCount(int filteType, int stype, String key, int stage, int ttype, String targetId, String parkId,int saleType,int dataType);

	List<OrderBean> selectOrders(int page, int pageSize, int filteType, int stype, String key, int stage, int ttype,
			String targetId, String parkId,int saleType,int dataType);

	OrderBean selectOrderById(String oid);

	int selectParkOrderNum(String pid);

	OrderStatusStatisticsBean selectOrderStatusCount(String aid);

	OrderStageStatisticsBean selectOrderStagesCount(String aid);

	int insertOrders(List<OrderBean> orders);

}
