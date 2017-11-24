package com.psp.sellcenter.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.psp.sellcenter.model.OrderContractBean;
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
			logger.info(providerJson);
			logger.info(JSON.toJSONString(proBean));
			order.setProviderJson(providerJson.toJSONString());
		}
		order.setSid(bean.getSid());
		order.setStage(bean.getStage());
		order.setStatus(bean.getStatus());
		order.setUid(bean.getUid());
		order.setUserJson(bean.getUserJson());
		order.setContent(bean.getContent());
		return order;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrder(String sid, String pid, String uid, String label, String content) {
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
		ProviderBean proBean = providerImpl.selectOneById(pid);
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", proBean.getPhoneNum());
		providerJson.put("contact", proBean.getContact());
		order.setProviderJson(providerJson.toJSONString());
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

		// TODO: 派单完发送短信
		flag = insertOrderLog(oid, orderNo, sid, sellerJson.toJSONString(),
				pid, providerJson.toJSONString(), 0, null);//0 创建并分配 1 编辑 2 派单 3 上传合同 4 调查反馈 5 归档
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
	private boolean insertOrderLog(String oid, String orderNo, String sid, String sellerJson, 
			String pid, String provider, int type, String content) {
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
		JSONObject contentJson = new JSONObject();
		// 0 创建并分配 1 编辑 2 派单 3 上传合同 4 确认完成 5 拒绝完成 6 调查反馈 7 归档关闭 
		contentJson.put("oid", oid);
		contentJson.put("orderNo", orderNo);
		if(type == 3) {
			contentJson.put("contractNo", content);// 上传合同的合同编号
		} else if(type == 7) {
			contentJson.put("reason", content);// 归档关闭的原因
		} else if(type == 4 || type == 5) {
			contentJson.put("opinion", content); // 销售对工单的完成意见
		}
		orderlog.setContent(contentJson.toJSONString());
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean allotOrder(String sid, String pid, String oid) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		ProviderBean proBean = providerImpl.selectOneById(pid);
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", proBean.getPhoneNum());
		providerJson.put("contact", proBean.getContact());
		
		if(order.getStatus() != 0) {
			throw new ServiceException("can_not_allot");
		}
		order.setStatus(2);// 等待服务商服务
		order.setStage(1);// 进行中
		order.setPid(pid);
		order.setIsAllot(1);//分配服务商
		flag = orderImpl.updateProvider(order) > 0;
		if(!flag) {
			throw new ServiceException("allot_order_error");
		}
		
		// TODO: 派单完发送短信
		flag = insertOrderLog(oid, order.getOrderNo(), sid, sellerJson.toJSONString(),
				pid, providerJson.toJSONString(), 2, null);//0 创建并分配 1 编辑 2 派单 3 上传合同 4 确认完成 5 拒绝完成 6 调查反馈 7 归档关闭
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean closeOrder(String sid, String oid, String content, int status) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单"+JSON.toJSONString(order));
		if((status == -1 && order.getStatus() != 0) // 待处理时关闭
				|| (status == -2 && order.getStatus() != 3) // 服务商已接受后，合同问题关闭
				|| (status == -3 && order.getStatus() != 8)) { // 服务商无法完成，客户提出终止
			throw new ServiceException("order_can_not_close");
		}
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		order.setStatus(status);
		order.setStage(3); // 阶段：关闭
		flag = orderImpl.updateStatus(order) > 0;
		if(!flag) {
			throw new ServiceException("allot_order_error");
		}
		flag = insertOrderLog(oid, order.getOrderNo(), sid, sellerJson.toJSONString(),
				null, null, 6, content);//0 创建并分配 1 编辑 2 派单 3 上传合同 4 确认完成 5 拒绝完成 6 调查反馈 7 归档关闭
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean uploadContract(String sid, String oid, String contractNo, String name, long signTime, long startTime,
			long endTime, String partyA, String partyB, String contractUrl, int payment, String paymentWay,
			String service, double money) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单"+JSON.toJSONString(order));
		if(order.getStatus() != 3) { // 服务商已接受状态后，销售可以与客户签合同
			throw new ServiceException("order_can_not_sign_contract");
		}
		OrderContractBean contract = new OrderContractBean();
		contract.setContractNo(contractNo);
		contract.setContractUrl(contractUrl);
		contract.setEndTime(new Timestamp(endTime));
		contract.setMoney(money);
		contract.setOid(oid);
		contract.setPartyA(partyA);
		contract.setPartyB(partyB);
		contract.setPayment(payment);
		contract.setPaymentWay(paymentWay);
		contract.setService(service);
		contract.setSignTime(new Timestamp(signTime));
		contract.setStartTime(new Timestamp(startTime));
		flag = orderImpl.insertContract(contract) > 0;
		if(!flag) {
			throw new ServiceException("upload_contract_error");
		}
		order.setStatus(4);// 合同已上传
		order.setExpectedTime(new Timestamp(endTime));// 合同结束时间更新到工单预计完成时间
		//TODO： 发送短信给服务商
		flag = orderImpl.updateStatus(order) > 0;
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		flag = insertOrderLog(oid, order.getOrderNo(), sid, sellerJson.toJSONString(),
				null, null, 3, contractNo);//0 创建并分配 1 编辑 2 派单 3 上传合同 4 确认完成 5 拒绝完成 6 调查反馈 7 归档关闭
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean confirmOrder(String sid, String oid, String content, int type) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单"+JSON.toJSONString(order));
		if(order.getStatus() != 5) { // 服务商申请完成，销售确认工单状态
			throw new ServiceException("order_can_not_confirm");
		}
		//1完成工单, 可反馈 2拒绝完成
		order.setStatus(type == 1 ? 6 : 7);
		flag = orderImpl.updateStatus(order) > 0;
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		int coType = type == 1 ? 4 : 5;
		ProviderBean proBean = providerImpl.selectOneById(order.getPid());
		JSONObject providerJson = new JSONObject();
		providerJson.put("name", proBean.getName());
		providerJson.put("phone", proBean.getPhoneNum());
		providerJson.put("contact", proBean.getContact());
		flag = insertOrderLog(oid, order.getOrderNo(), sid, sellerJson.toJSONString(),
				order.getPid(), providerJson.toJSONString(), coType, content);//0 创建并分配 1 编辑 2 派单 3 上传合同 4 确认完成 5 拒绝完成 6 调查反馈 7 归档关闭
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean feedback(String sid, String oid, String content, String score) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		OrderBean order = orderImpl.selectOrderById(oid);
		if(order == null) {
			throw new ServiceException("object_is_not_exist", "工单");
		}
		logger.info("工单"+JSON.toJSONString(order));
		if(order.getStatus() != 6) { // 6 销售确认完成，等待反馈状态
			throw new ServiceException("order_can_not_feedback");
		}
		order.setStatus(1);// 1 已完成
		order.setStage(2);// 阶段已完成
		flag = orderImpl.updateStatus(order) > 0;
		// TODO：给服务商评分
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		if(!flag) {
			throw new ServiceException("update_order_status_error");
		}
		JSONObject contentJson = new JSONObject();
		contentJson.put("score", JSON.parse(score));
		contentJson.put("describe", content);
		flag = insertOrderLog(oid, order.getOrderNo(), sid, sellerJson.toJSONString(),
				null, null, 6, contentJson.toJSONString());//0 创建并分配 1 编辑 2 派单 3 上传合同 4 确认完成 5 拒绝完成 6 调查反馈 7 归档关闭
		if(!flag) {
			throw new ServiceException("create_order_log_error");
		}
		return flag;
	}
	

}