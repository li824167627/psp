package com.psp.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psp.provider.controller.res.bean.ROrderBean;
import com.psp.provider.controller.res.bean.ROrderContractBean;
import com.psp.provider.controller.res.bean.ROrderFeedbackBean;
import com.psp.provider.controller.res.bean.ROrderLogsBean;
import com.psp.provider.model.AccountBean;
import com.psp.provider.model.OrderBean;
import com.psp.provider.model.OrderContractBean;
import com.psp.provider.model.OrderFeedbackBean;
import com.psp.provider.model.OrderLogBean;
import com.psp.provider.model.ProviderBean;
import com.psp.provider.model.SellerBean;
import com.psp.provider.model.UserBean;
import com.psp.provider.persist.dao.OrderDao;
import com.psp.provider.persist.dao.OrderLogDao;
import com.psp.provider.persist.dao.ProviderDao;
import com.psp.provider.service.OrderService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.provider.service.impl.res.PageResult;
import com.psp.util.StringUtil;

@Service
public class OrderServiceImpl implements OrderService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderDao orderImpl;
	
	@Autowired
	OrderLogDao orderLogImpl;

	@Autowired
	ProviderDao providerImpl;
	
	final String qiniulinkurl = "http://os4z3g2v6.bkt.clouddn.com/";


	@Override
	public PageResult<ROrderBean> getOrders(AccountBean account, int page, int pageSize, int filteType, int stype,
			String key, int stage) {
		PageResult<ROrderBean> result = new PageResult<ROrderBean>();
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}	
		String pid = account.getPid();
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider == null) {
			throw new ServiceException("object_is_not_exist", "当前服务商");
		}
		int count = orderImpl.selectOrderCount(provider.getPid(), filteType, stype, key, stage);
		if(count == 0) {
			return null;
		}
		List<OrderBean> resList = orderImpl.selectOrders(page, pageSize, provider.getPid(), filteType, stype, key, stage);
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
		
		if(bean.getSeller() != null) {
			SellerBean seller = bean.getSeller();
			JSONObject sellerJson = new JSONObject();
			sellerJson.put("sid", seller.getSid());
			sellerJson.put("name", seller.getUsername());
			sellerJson.put("phone", seller.getPhoneNum());
			order.setSellerJson(sellerJson.toJSONString());
		}
		
		if(bean.getUser() != null) {
			UserBean userBean = bean.getUser();
			JSONObject userJson = new JSONObject();
			userJson.put("name", userBean.getName());
			userJson.put("phone", userBean.getPhoneNum());
			order.setUserJson(userJson.toJSONString());
		}
		
		if(bean.getContracts() != null) {
			List<OrderContractBean> contracts = bean.getContracts();
			List<ROrderContractBean> recontracts = new ArrayList<ROrderContractBean>();
			logger.info("合同：" + JSON.toJSON(contracts));
			if(contracts.size() > 0) {
				for(OrderContractBean con : contracts) {
					recontracts.add(parse(con));
				}
			}
			order.setContracts(recontracts);
		}
		if(bean.getFeedback() != null) {
			OrderFeedbackBean feed = bean.getFeedback();
			ROrderFeedbackBean rfeed = new ROrderFeedbackBean();
			rfeed.setAverageScore(feed.getAverageScore());
			rfeed.setFid(feed.getFid());
			rfeed.setContent(feed.getContent());
			rfeed.setSuggestion(feed.getSuggestion());
			order.setFeedback(rfeed);
		}
		order.setSid(bean.getSid());
		order.setStage(bean.getStage());
		order.setStatus(bean.getStatus());
		order.setUid(bean.getUid());
		order.setContent(bean.getContent());
		return order;
	}
	private ROrderContractBean parse(OrderContractBean con) {
		ROrderContractBean rcontract = new ROrderContractBean();
		rcontract.setCid(con.getCid());
		rcontract.setContractNo(con.getContractNo());
		rcontract.setContractUrl(qiniulinkurl + con.getContractUrl());
		if(con.getEndTime() != null) {
			rcontract.setEndTime(con.getEndTime().getTime() / 1000);
		}
		if(con.getSignTime() != null) {
			rcontract.setSignTime(con.getSignTime().getTime() / 1000);
		}
		if(con.getStartTime() != null) {
			rcontract.setStartTime(con.getStartTime().getTime() / 1000);
		}
		rcontract.setMoney(con.getMoney());
		rcontract.setOid(con.getOid());
		rcontract.setPartyA(con.getPartyA());
		rcontract.setPartyB(con.getPartyB());
		rcontract.setPayment(con.getPayment());
		rcontract.setPaymentDesc(con.getPaymentDesc());
		rcontract.setPaymentWay(con.getPaymentWay());
		rcontract.setName(con.getName());
		
		
		return rcontract;
	}

	

	@Override
	public int getOrderNum2Provider(AccountBean account, int stage) {
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}	
		String pid = account.getPid();
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider == null) {
			throw new ServiceException("object_is_not_exist", "当前服务商");
		}
		return orderImpl.selectOrderCount2Provider(provider.getPid(), stage);
	}


	@Override
	public ROrderBean getDetail(AccountBean account, String oid) {
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("order:" + JSON.toJSONString(order));
		logger.info("account:" + JSON.toJSONString(account));
		if(!account.getPid().equals(order.getPid())) {
			throw new ServiceException("account_has_no_auth", "工单");
		}
		return parse(order);
	}


	@Override
	public PageResult<ROrderLogsBean> getOrderogs(AccountBean account, String oid, String key) {
		PageResult<ROrderLogsBean> result = new PageResult<ROrderLogsBean>();
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean submitFinish(String oid, int type, String content, AccountBean account) {
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}
		boolean flag = false;
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单： "+JSON.toJSONString(order));
		if(order.getStatus() != 4 && order.getStatus() != 7) { //4 合同已上传 7 拒绝完成
			throw new ServiceException("order_can_not_submit");
		}
		order.setStatus(5);
		flag = orderImpl.updateStatus(order) > 0;
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		String pid = account.getPid();
		ProviderBean proBean = providerImpl.selectOneById(order.getPid());
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", account.getPhoneNum());
		providerJson.put("contact", account.getUsername());
		// 8 接收 9 拒绝 10 申请完成 11 申请终止
		flag = insertOrderLog(oid, order.getOrderNo(), pid, providerJson.toJSONString(), 10, content, type);
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean refuseOrder(String oid, String content, AccountBean account) {
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}
		boolean flag = false;
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单： "+JSON.toJSONString(order));
		if(order.getStatus() != 2) { //2 待处理
			throw new ServiceException("order_can_not_handle");
		}
		order.setPid(null);
		order.setIsAllot(0);
		order.setStatus(0);//已拒绝，status=0 待处理
		flag = orderImpl.refuseOrder(order) > 0;
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		String pid = account.getPid();
		ProviderBean proBean = providerImpl.selectOneById(pid);
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", account.getPhoneNum());
		providerJson.put("contact", account.getUsername());
		// 8 接收 9 拒绝 10 申请完成 11 申请终止
		flag = insertOrderLog(oid, order.getOrderNo(), pid, providerJson.toJSONString(), 9, content, 0);
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean acceptOrder(String oid, String content, AccountBean account) {
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}
		boolean flag = false;
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单： "+JSON.toJSONString(order));
		if(order.getStatus() != 2) { //2 待处理
			throw new ServiceException("order_can_not_handle");
		}
		order.setStatus(3);//已接收，status=3
		order.setStage(1);
		flag = orderImpl.updateStatus(order) > 0;
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		String pid = account.getPid();
		ProviderBean proBean = providerImpl.selectOneById(order.getPid());
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", account.getPhoneNum());
		providerJson.put("contact", account.getUsername());
		// 8 接收 9 拒绝 10 申请完成 11 申请终止
		flag = insertOrderLog(oid, order.getOrderNo(), pid, providerJson.toJSONString(), 8, content, 0);
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean submitClose(String oid, int type, String content, AccountBean account) {
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "登录账户");
		}
		boolean flag = false;
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单： "+JSON.toJSONString(order));
		if(order.getStatus() != 4 && order.getStatus() != 7) { //4 合同已上传 7 拒绝完成
			throw new ServiceException("order_can_not_submit");
		}
		order.setStatus(8);//申请终止，status=8
		order.setStage(1);
		flag = orderImpl.updateStatus(order) > 0;
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		String pid = account.getPid();
		ProviderBean proBean = providerImpl.selectOneById(order.getPid());
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", account.getPhoneNum());
		providerJson.put("contact", account.getUsername());
		// 8 接收 9 拒绝 10 申请完成 11 申请终止
		flag = insertOrderLog(oid, order.getOrderNo(), pid, providerJson.toJSONString(), 11, content, type);
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
	 * @param content 
	 * @return
	 */
	private boolean insertOrderLog(String oid, String orderNo, String pid, String provider, int type, String content, int select) {
		OrderLogBean orderlog = new OrderLogBean();
		orderlog.setOid(oid);
		if(!StringUtil.isEmpty(pid)) {
			orderlog.setPid(pid);
			orderlog.setProviderJson(provider);
		}
		JSONObject contentJson = new JSONObject();
		// 8 接收 9 拒绝 10 申请完成 11 申请终止
		contentJson.put("oid", oid);
		contentJson.put("orderNo", orderNo);
		contentJson.put("content", content);
		if(type == 10) {
			contentJson.put("type", select == 1 ? "按期完成" : select == 2 ? "延期完成" : "其他");
		} else if(type == 11) {
			contentJson.put("type", select == 1 ? "客户终止" : "其他");
		}
		orderlog.setContent(contentJson.toJSONString());
		orderlog.setType(type);
		return orderLogImpl.insert(orderlog) > 0;
	}


}
