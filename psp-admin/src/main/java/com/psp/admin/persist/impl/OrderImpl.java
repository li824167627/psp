package com.psp.admin.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.northend.api.go.util.StringUtil;
import com.psp.admin.model.OrderBean;
import com.psp.admin.model.OrderStageStatisticsBean;
import com.psp.admin.model.OrderStatusStatisticsBean;
import com.psp.admin.persist.dao.OrderDao;

@Repository
public class OrderImpl extends BaseImpl implements OrderDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".OrderMapper";

	@Override
	public int selectOrderCount(int filteType, int stype, String key, int stage, int ttype, String targetId, String parkId) {
		Map<String, Object> where = new HashMap<>();
		where.put("filteType", filteType);
		where.put("stype", stype);
		if(!StringUtil.isEmpty(key)) {
			where.put("key", key);
		}
		where.put("stage", stage);
		switch (ttype) {//0:全部1:服务商2:客户3:销售
			case 1:
				where.put("pid", targetId);
				break;
			case 2:
				where.put("uid", targetId);
				break;
			case 3:
				where.put("sid", targetId);
				break;
			default:
				break;
		}
		if(!StringUtil.isEmpty(parkId)) {
			where.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderCount", where);
	}

	@Override
	public List<OrderBean> selectOrders(int page, int pageSize, int filteType, int stype, String key, int stage,
			int ttype, String targetId, String parkId) {
		Map<String, Object> where = new HashMap<>();
		where.put("start", page * pageSize);
		where.put("length", pageSize);
		where.put("filteType", filteType);
		where.put("stype", stype);
		if(!StringUtil.isEmpty(key)) {
			where.put("key", key);
		}
		where.put("stage", stage);
		switch (ttype) {//0:全部1:服务商2:客户3:销售
			case 1:
				where.put("pid", targetId);
				break;
			case 2:
				where.put("uid", targetId);
				break;
			case 3:
				where.put("sid", targetId);
				break;
			default:
				break;
		}
		if(!StringUtil.isEmpty(parkId)) {
			where.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectOrders", where);
	}


	@Override
	public OrderBean selectOrderById(String oid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderById", oid);
	}

	@Override
	public int selectParkOrderNum(String pid) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectParkOrderCount", pid);
	}

	@Override
	public OrderStatusStatisticsBean selectOrderStatusCount(String parkId) {
		Map<String, Object> where = new HashMap<>();
		if(!StringUtil.isEmpty(parkId)) {
			where.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderStatusCount", where);

	}

	@Override
	public OrderStageStatisticsBean selectOrderStagesCount(String parkId) {
		Map<String, Object> where = new HashMap<>();
		if(!StringUtil.isEmpty(parkId)) {
			where.put("parkId", parkId);
		}
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectOrderStagesCount", where);

	}

	@Override
	public int insertOrders(List<OrderBean> orders) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insertOrders", orders);
	}

}
