package com.psp.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psp.admin.controller.res.bean.ROrderBean;
import com.psp.admin.controller.res.bean.ROrderContractBean;
import com.psp.admin.controller.res.bean.ROrderFeedbackBean;
import com.psp.admin.controller.res.bean.ROrderLogsBean;
import com.psp.admin.model.AdminBean;
import com.psp.admin.model.OrderBean;
import com.psp.admin.model.OrderContractBean;
import com.psp.admin.model.OrderFeedbackBean;
import com.psp.admin.model.OrderLogBean;
import com.psp.admin.model.ProviderBean;
import com.psp.admin.model.UserBean;
import com.psp.admin.model.excel.OrderInfoBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.persist.dao.OrderDao;
import com.psp.admin.persist.dao.OrderLogDao;
import com.psp.admin.persist.dao.UserDao;
import com.psp.admin.service.OrderService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.NumUtil;
import com.psp.util.excel.ImportExcel;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderDao orderImpl;

	@Autowired
	AdminDao adminImpl;

	@Autowired
	UserDao userImpl;
	
	@Autowired
	OrderLogDao orderLogImpl;

	final String qiniulinkurl = "http://os4z3g2v6.bkt.clouddn.com/";

	@Override
	public PageResult<ROrderBean> getOrders(String adminId, int page, int pageSize, int filteType, int stype,
			String key, String targetId, int ttype, int stage) {
		PageResult<ROrderBean> result = new PageResult<ROrderBean>();
		AdminBean admin = adminImpl.selectOneById(adminId);
		String parkId = null;
		if(admin.getType() == 0) {
			parkId = admin.getPid();
		}
		int count = orderImpl.selectOrderCount(filteType, stype, key, stage, ttype, targetId, parkId);
		if(count == 0) {
			return null;
		}
		List<OrderBean> resList = orderImpl.selectOrders(page, pageSize,filteType, stype, key, stage, ttype, targetId, parkId);
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
		order.setDataType(bean.getDataType());
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
	public int getOrderNum(String adminId, int stage) {
		int count = orderImpl.selectOrderCount(0, 0, null, stage, 0, null, null);
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean ImportOrders(HttpServletRequest request) throws Exception {
		boolean flag = false;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 不是文件
		if (!multipartResolver.isMultipart(request)) {
			throw new ServiceException("param_is_error", "文件");
		}
		// 转换成多部分request
		MultipartHttpServletRequest multRequest = (MultipartHttpServletRequest) request;
		File excelfile = null;
		try {
			// 获得文件：   
	        MultipartFile file = multRequest.getFile("file");   
	        excelfile = File.createTempFile("tmp", null);
	        // 读取excel内容
	        ImportExcel<OrderInfoBean> test = new ImportExcel<OrderInfoBean>(OrderInfoBean.class);  
	        file.transferTo(excelfile);      
	        Long befor = System.currentTimeMillis();  
	        List<OrderInfoBean> result = (ArrayList<OrderInfoBean>) test.importExcel(excelfile);  
	        List<OrderBean> orders = new ArrayList<>();
	        List<UserBean> users = userImpl.selectUsersByType(2);
			logger.info(JSON.toJSON("获取所有客户：" + users));
			Map<String, String> AllUsers = new HashMap<String, String>();  
		    for (UserBean user : users) { 
		    		AllUsers.put(user.getCompanyName(), user.getUid());  
		    }  
	        if(result != null && result.size() > 0) {
	        		for(OrderInfoBean o : result) {
	        			OrderBean order = new OrderBean();
	        			order.setOid(AppTextUtil.getPrimaryKey());
	        			String prefix = "GD";// 工单前缀
	        			String orderNo = AppTextUtil.getRandomNo(prefix);
	        			order.setOrderNo(orderNo);
	        			order.setContent(o.getContent());
	        			order.setDataType(2);// 1正常录入2补充数据0测试数据
	        			order.setIsAllot(1);// 已分配
	        			order.setPid(o.getProvider());
	        			order.setSid(o.getSeller());
	        			order.setMoney(NumUtil.toDouble(o.getMoney(),0));
	        			order.setUid(AllUsers.get(o.getUser()));
	        			order.setContractStatus(0);
	        			order.setStage(2);// 已完成
	        			order.setStatus(1);// 已完成
	        			orders.add(order);
	        		}
	        		logger.info("page-289 : " + JSON.toJSONString(orders) );
	        		if(orders.size() > 0) {
	        			flag = orderImpl.insertOrders(orders) > 0;
	        			if(!flag) {
	        				throw new ServiceException("import_excel_error");
	        			}
	        		}
	        		
	        }
	        
	        Long after = System.currentTimeMillis();  
	        System.out.println("此次操作共耗时：" + (after - befor) + "毫秒");  
	        excelfile.deleteOnExit();
			
		} catch (Exception e) {
			throw e;
		}   
		return flag;
	}


}
