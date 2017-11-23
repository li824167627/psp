package com.psp.sellcenter.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psp.sellcenter.model.OrderBean;
import com.psp.sellcenter.persist.dao.OrderDao;

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


}
