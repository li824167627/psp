package com.psp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psp.admin.controller.res.bean.ROrderBean;
import com.psp.admin.controller.res.bean.ROrderLogsBean;
import com.psp.admin.model.OrderBean;
import com.psp.admin.model.OrderLogBean;
import com.psp.admin.model.ProviderBean;
import com.psp.admin.model.UserBean;
import com.psp.admin.persist.dao.OrderDao;
import com.psp.admin.persist.dao.OrderLogDao;
import com.psp.admin.service.OrderService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderDao orderImpl;
	
	@Autowired
	OrderLogDao orderLogImpl;

	@Override
	public PageResult<ROrderBean> getOrders(String adminId, int page, int pageSize, int filteType, int stype,
			String key, String targetId, int ttype, int stage) {
		PageResult<ROrderBean> result = new PageResult<ROrderBean>();
		int count = orderImpl.selectOrderCount(filteType, stype, key, stage, ttype, targetId);
		if(count == 0) {
			return null;
		}
		List<OrderBean> resList = orderImpl.selectOrders(page, pageSize,filteType, stype, key, stage, ttype, targetId);
		List<ROrderBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (OrderBean bean : resList) {
				ROrderBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}
	
	/**
	 * 格式化数据
	 * @param bean
	 * @return
	 */
	private ROrderBean parse(OrderBean bean) {
		ROrderBean order = new ROrderBean();
		if(bean.getCompleteTime() != null) {
			order.setCompleteTime(bean.getCompleteTime().getTime() / 1000);
		}
		bean.setContent(bean.getContent());
		if(bean.getCreateTime() != null) {
			order.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		if(bean.getExpectedTime() != null) {
			order.setExpectedTime(bean.getExpectedTime().getTime() / 1000);
		}
		if(bean.getCloseTime() != null) {
			order.setCloseTime(bean.getCloseTime().getTime() / 1000);
		}
		if(bean.getUpdateTime() != null) {
			order.setUpdateime(bean.getUpdateTime().getTime() / 1000);
		}
		order.setIsAllot(bean.getIsAllot());
		order.setIsNeedInvoice(bean.getIsNeedInvoice());
		order.setLabel(bean.getLabel());
		order.setOid(bean.getOid());
		order.setOrderNo(bean.getOrderNo());
		order.setPid(bean.getPid());
		
		if(bean.getProvider() != null) {
			ProviderBean proBean = bean.getProvider();
			JSONObject providerJson = new JSONObject();
			providerJson.put("name", proBean.getName());
			providerJson.put("phone", proBean.getPhoneNum());
			providerJson.put("contact", proBean.getContact());
			order.setProviderJson(providerJson.toJSONString());
		}
		

		if(bean.getUser() != null) {
			UserBean userBean = bean.getUser();
			JSONObject userJson = new JSONObject();
			userJson.put("name", userBean.getName());
			userJson.put("phone", userBean.getPhoneNum());
			userJson.put("companyName", userBean.getCompanyName());
			order.setUserJson(userJson.toJSONString());
		}
		order.setSid(bean.getSid());
		order.setStage(bean.getStage());
		order.setStatus(bean.getStatus());
		order.setUid(bean.getUid());
		order.setContent(bean.getContent());
		return order;
	}

	@Override
	public int getOrderNum(String adminId, int stage) {
		int count = orderImpl.selectOrderCount(0, 0, null, stage, 0, null);
		return count;
	}

	@Override
	public ROrderBean getDetail(String adminId, String oid) {
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		return parse(order);
	}

	@Override
	public PageResult<ROrderLogsBean> getOrderLogs(String sid, String oid, String key) {
		PageResult<ROrderLogsBean> result = new PageResult<ROrderLogsBean>();
		int count = orderLogImpl.selectOrderLogsCount(oid, key);
		if(count == 0) {
			return null;
		}
		List<OrderLogBean> resList = orderLogImpl.selectOrderLogs(oid, key);
		List<ROrderLogsBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (OrderLogBean bean : resList) {
				ROrderLogsBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}
	
	/**
	 * 格式化工单操作日志信息
	 * @param bean
	 * @return
	 */
	private ROrderLogsBean parse(OrderLogBean bean) {
		ROrderLogsBean log = new ROrderLogsBean();
		log.setContent(bean.getContent());
		log.setLid(bean.getLid());
		log.setOid(bean.getOid());
		log.setPid(bean.getPid());
		log.setProviderJson(bean.getProviderJson());
		log.setSellerJson(bean.getSellerJson());
		log.setSid(bean.getSid());
		log.setType(bean.getType());
		if(bean.getCreateTime() != null) {
			log.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return log;
	}


}
