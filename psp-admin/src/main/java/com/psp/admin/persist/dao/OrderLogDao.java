package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.OrderLogBean;

public interface OrderLogDao {

	int selectOrderLogsCount(String oid, String key);

	List<OrderLogBean> selectOrderLogs(String oid, String key);

}
