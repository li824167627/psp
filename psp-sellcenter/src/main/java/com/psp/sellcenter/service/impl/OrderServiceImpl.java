package com.psp.sellcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psp.sellcenter.cache.dao.ServiceCacheDao;
import com.psp.sellcenter.controller.res.bean.ROrderBean;
import com.psp.sellcenter.controller.res.bean.ROrderLogsBean;
import com.psp.sellcenter.controller.res.bean.RServiceProviderBean;
import com.psp.sellcenter.model.CategoryBean;
import com.psp.sellcenter.model.CategoryTree;
import com.psp.sellcenter.model.OrderBean;
import com.psp.sellcenter.model.OrderLogBean;
import com.psp.sellcenter.model.ProviderBean;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.model.UserBean;
import com.psp.sellcenter.persist.dao.OrderDao;
import com.psp.sellcenter.persist.dao.OrderLogDao;
import com.psp.sellcenter.persist.dao.ProviderDao;
import com.psp.sellcenter.persist.dao.SellerDao;
import com.psp.sellcenter.persist.dao.UserDao;
import com.psp.sellcenter.service.OrderService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.StringUtil;

@Service
public class OrderServiceImpl implements OrderService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	OrderDao orderImpl;
	
	@Autowired
	OrderLogDao orderLogImpl;

	@Autowired
	UserDao userImpl;
	
	@Autowired
	ProviderDao providerImpl;

	@Autowired
	ServiceCacheDao serviceCacheImpl;

	@Override
	public PageResult<ROrderBean> getOrders(String sid, int page, int pageSize, int filteType, int stype,
			String key, String uid, int stage) {
		PageResult<ROrderBean> result = new PageResult<ROrderBean>();
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}	
		int count = orderImpl.selectOrderCount(sid, filteType, stype, key, uid, stage);
		if(count == 0) {
			return null;
		}
		List<OrderBean> resList = orderImpl.selectOrders(page, pageSize, sid, filteType, stype, key, uid, stage);
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
		order.setIsAllot(bean.getIsAllot());
		order.setIsNeedInvoice(bean.getIsNeedInvoice());
		order.setLabel(bean.getLabel());
		order.setOid(bean.getOid());
		order.setOrderNo(bean.getOrderNo());
		order.setPid(bean.getPid());
		order.setProviderJson(bean.getProviderJson());
		order.setSid(bean.getSid());
		order.setStage(bean.getStage());
		order.setStatus(bean.getStatus());
		order.setUid(bean.getUid());
		order.setUserJson(bean.getUserJson());
		return order;
	}

	@Override
	public boolean addOrder(String sid, String pid, String provider, String uid, String label, String content) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		UserBean user = userImpl.selectUserById(uid);
		logger.info(JSON.toJSONString(user));
		if(user == null) {
			throw new ServiceException("object_is_not_exist", "客户");
		}
		OrderBean order = new OrderBean();
		String oid = AppTextUtil.getPrimaryKey();
		String prefix = "GD";// 工单前缀
		String orderNo = AppTextUtil.getRandomNo(prefix);
		order.setOid(oid);
		order.setOrderNo(orderNo);
		order.setPid(pid);
		order.setProviderJson(provider);
		order.setUid(uid);
		order.setSid(sid);
		order.setSellerJson(sellerJson.toJSONString());
		order.setLabel(label);
		order.setContent(content);
		order.setStatus(2);// 待处理
		order.setStage(1);// 进行中
		order.setIsAllot(1);// 已分配
		flag = orderImpl.insert(order) > 0;
		if(!flag) {
			throw new ServiceException("create_order_error");
		}
		flag = insertOrderLog(oid, orderNo, sid, sellerJson.toJSONString(), pid, provider, 0, user);//0 创建并分配 1 编辑 2 派单 3 上传合同 4 调查反馈 5 归档
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}
	
	/**
	 * 插入订单操作日志表
	 * @param oid
	 * @param sid
	 * @param jsonString
	 * @param pid
	 * @param provider
	 * @param type
	 * @return
	 */
	private boolean insertOrderLog(String oid, String orderNo, String sid, String sellerJson, 
			String pid, String provider, int type, UserBean user) {
		OrderLogBean orderlog = new OrderLogBean();
		orderlog.setOid(oid);
		if(!StringUtil.isEmpty(sid)) {
			orderlog.setSid(sid);
			orderlog.setSellerJson(sellerJson);
		}
		if(!StringUtil.isEmpty(pid)) {
			orderlog.setPid(pid);
			orderlog.setProviderJson(provider);
		}
		JSONObject userJson = new JSONObject();
		userJson.put("name", user.getName());
		userJson.put("phoneNum", user.getPhoneNum());
		userJson.put("companyName", user.getCompanyName());
		userJson.put("position", user.getPosition());
		// 0 创建并分配 1 编辑 2 派单 3 上传合同 4 调查反馈 5 归档
		userJson.put("oid", oid);
		userJson.put("orderNo", orderNo);
		orderlog.setContent(userJson.toJSONString());
		orderlog.setType(type);
		return orderLogImpl.insert(orderlog) > 0;
	}

	@Override
	public RServiceProviderBean getServiceProviders() {
		RServiceProviderBean bean = new RServiceProviderBean();
		JSONArray jsonArray = new JSONArray();
		String cateStr = serviceCacheImpl.getCategoryCache();
		if (StringUtil.isEmpty(cateStr)) {
			List<CategoryBean> cates = providerImpl.selectAllCates();
			if(cates == null) {
				return null;
			}
			for(CategoryBean ca : cates) {
				JSONObject firstObject = new JSONObject();
				firstObject.put("name", ca.getName());
				firstObject.put("cid", ca.getCid());
				List<CategoryBean> children = ca.getChildern();
				List<CategoryTree> sub = null;
				if(children != null && children.size() > 0) {
					sub = new ArrayList<CategoryTree>();
					for(CategoryBean c : children){
						List<ProviderBean> providers = providerImpl.selectListByCid(c.getCid());
						CategoryTree menus = new CategoryTree(c.getName(), c.getCid(), providers);
						sub.add(menus);
					}
				}
				firstObject.put("children", sub);
				jsonArray.add(firstObject);
			}
			String jsonMenu = JSON.toJSONString(jsonArray);
			serviceCacheImpl.setCategoryCache(jsonMenu);
		} else {
			jsonArray = JSON.parseArray(cateStr);
		}
		bean.setService(jsonArray);
		return bean;
	}

	@Override
	public int getOrderNum2Seller(String sid, int stage) {
		int count = orderImpl.selectOrderCount2Seller(sid,stage);
		return count;
	}

	@Override
	public ROrderBean getDetail(String sid, String oid) {
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		return parse(order);
	}

	@Override
	public PageResult<ROrderLogsBean> getOrderogs(String sid, String oid, String key) {
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
		log.setProviderJon(bean.getProviderJson());
		log.setSellerJson(bean.getSellerJson());
		log.setSid(bean.getSid());
		log.setType(bean.getType());
		if(bean.getCreateTime() != null) {
			log.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return log;
	}
	

}
