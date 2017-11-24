package com.psp.provider.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.provider.model.OrderBean;
import com.psp.provider.persist.dao.OrderDao;

@Repository
public class OrderImpl extends BaseImpl implements OrderDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".OrderMapper";

	@Override
	public int selectOrderCount(String pid, int filteType, int stype, String key, int stage) {
		Map<String, Object> where = new HashMap<>();
		where.put("pid", pid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("stage", stage);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderCount", where);
	}

	@Override
	public List<OrderBean> selectOrders(int page, int pageSize, String pid, int filteType, int stype, String key, int stage) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("pid", pid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("stage", stage);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectOrders", where);
	}

	@Override
	public int selectOrderCount2Provider(String pid, int stage) {
		Map<String, Object> where = new HashMap<>();
		where.put("pid", pid);
		where.put("stage", stage);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderCount2Provider", where);
	
	}

	@Override
	public OrderBean selectOrderById(String oid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderById", oid);
		
	}
	
	@Override
	public int updateStatus(OrderBean order) {
		Map<String, Object> where = new HashMap<>();
		where.put("oid", order.getOid());
		where.put("status", order.getStatus());
		return sqlSessionTemplate.update(NAME_SPACE + ".updateStatus", where);
	}

	@Override
	public int refuseOrder(OrderBean order) {
		Map<String, Object> where = new HashMap<>();
		where.put("oid", order.getOid());
		where.put("status", order.getStatus());
		where.put("pid", order.getPid());
		where.put("isAllot", order.getIsAllot());
		return sqlSessionTemplate.update(NAME_SPACE + ".refuseOrder", where);
	}


}
