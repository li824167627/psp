package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.admin.model.OrderLogBean;
import com.psp.admin.persist.dao.OrderLogDao;

@Repository
public class OrderLogImpl extends BaseImpl implements OrderLogDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".OrderLogMapper";

	@Override
	public int selectOrderLogsCount(String oid, String key) {
		Map<String, Object> where = new HashMap<>();
		where.put("oid", oid);
		where.put("key", key);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderLogsCount", where);

	}

	@Override
	public List<OrderLogBean> selectOrderLogs(String oid, String key) {
		Map<String, Object> where = new HashMap<>();
		where.put("oid", oid);
		where.put("key", key);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectOrderLogs", where);
	
	}

}
