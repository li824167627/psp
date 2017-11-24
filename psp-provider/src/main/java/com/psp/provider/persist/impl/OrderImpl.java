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
	public int selectOrderCount(String sid, int filteType, int stype, String key, String uid, int stage) {
		Map<String, Object> where = new HashMap<>();
		where.put("sid", sid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("uid", uid);
		where.put("stage", stage);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderCount", where);
	}

	@Override
	public List<OrderBean> selectOrders(int page, int pageSize, String sid, int filteType, int stype, String key, String uid, int stage) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("sid", sid);
		where.put("filteType", filteType);
		where.put("stype", stype);
		where.put("key", key);
		where.put("uid", uid);
		where.put("stage", stage);
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectOrders", where);
	}

	@Override
	public int insert(OrderBean order) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", order);
	}

	@Override
	public int selectOrderCount2Seller(String sid, int stage) {
		Map<String, Object> where = new HashMap<>();
		where.put("sid", sid);
		where.put("stage", stage);
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderCount2Seller", where);
	}

	@Override
	public OrderBean selectOrderById(String oid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderById", oid);
	}

	@Override
	public int updateProvider(OrderBean order) {
		Map<String, Object> where = new HashMap<>();
		where.put("pid", order.getPid());
		where.put("oid", order.getOid());
		where.put("status", order.getStatus());
		where.put("stage", order.getStage());
		return sqlSessionTemplate.update(NAME_SPACE + ".updateProvider", where);

	}

	@Override
	public int updateStatus(OrderBean order) {
		Map<String, Object> where = new HashMap<>();
		where.put("oid", order.getOid());
		where.put("status", order.getStatus());
		where.put("stage", order.getStage());
		where.put("expectedTime", order.getExpectedTime());
		return sqlSessionTemplate.update(NAME_SPACE + ".updateStatus", where);

	}


}
