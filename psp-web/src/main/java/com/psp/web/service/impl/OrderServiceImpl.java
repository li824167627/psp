package com.psp.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psp.util.AppTextUtil;
import com.psp.util.StringUtil;
import com.psp.web.cache.dao.ServiceCacheDao;
import com.psp.web.controller.res.bean.RCategoryBean;
import com.psp.web.controller.res.bean.RCategoryJSONBean;
import com.psp.web.model.CategoryBean;
import com.psp.web.model.UserBean;
import com.psp.web.model.UserLogBean;
import com.psp.web.model.UserNewsBean;
import com.psp.web.persist.dao.ServiceDao;
import com.psp.web.persist.dao.UserDao;
import com.psp.web.service.OrderService;
import com.psp.web.service.exception.ServiceException;
import com.psp.web.service.res.PageResult;

@Service
public class OrderServiceImpl implements OrderService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserDao userImpl;
	
	@Autowired
	ServiceDao serviceImpl;

	@Autowired
	ServiceCacheDao serviceCacheImpl;

	@Override
	public RCategoryJSONBean getAllServices() {
		RCategoryJSONBean bean = new RCategoryJSONBean();
		JSONArray jsonArray = new JSONArray();
		String cateStr = serviceCacheImpl.getAllCategoryCache();
		if (StringUtil.isEmpty(cateStr)) {
			List<CategoryBean> cates = serviceImpl.selectAllCates();
			if(cates == null) {
				return null;
			}
			// 获取所有服务项
			List<CategoryBean> Services = serviceImpl.selectService(null);
			Map<Integer, JSONArray> AllServices = new HashMap<Integer, JSONArray>();  
			JSONArray subCates = new JSONArray();
		    for (CategoryBean cate : Services) { 
			 	JSONObject serviceObject = new JSONObject();
		    		serviceObject.put("name", cate.getName());
		    		serviceObject.put("cid", cate.getCid());
			    if(AllServices.containsKey(cate.getParentId())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中  
			    		AllServices.get(cate.getParentId()).add(serviceObject);
	            }else{//map中不存在，新建key，用来存放数据  
	            		subCates = new JSONArray();
	            		subCates.add(serviceObject);
			    		AllServices.put(cate.getParentId(), subCates); 
	            }  
		    }  
		    
		    // 构造三级树
			for(CategoryBean ca : cates) {
				JSONObject firstObject = new JSONObject();
				firstObject.put("name", ca.getName());
				firstObject.put("cid", ca.getCid());
				List<CategoryBean> children = ca.getChildern();
				JSONArray secondCates = new JSONArray();
				if(children != null && children.size() > 0) {
					for(CategoryBean c : children){
						JSONObject secondObject = new JSONObject();
						secondObject.put("name", c.getName());
						secondObject.put("cid", c.getCid());
						secondObject.put("children", AllServices.get(c.getCid()));
						secondCates.add(secondObject);
					}
				}
				firstObject.put("children", secondCates);
				jsonArray.add(firstObject);
			}
			String jsonMenu = JSON.toJSONString(jsonArray);
			serviceCacheImpl.setAllCategoryCache(jsonMenu);
		} else {
			jsonArray = JSON.parseArray(cateStr);
		}
		bean.setCategory(jsonArray);
		return bean;
	}

	@Override
	public RCategoryJSONBean getCategories() {
		RCategoryJSONBean bean = new RCategoryJSONBean();
		JSONArray jsonArray = new JSONArray();
		String cateStr = serviceCacheImpl.getCategoryCache();
		if (StringUtil.isEmpty(cateStr)) {
			List<CategoryBean> cates = serviceImpl.selectAllCates();
			if(cates == null) {
				return null;
			}
		    // 构造三级树
			for(CategoryBean ca : cates) {
				JSONObject firstObject = new JSONObject();
				firstObject.put("name", ca.getName());
				firstObject.put("cid", ca.getCid());
				List<CategoryBean> children = ca.getChildern();
				JSONArray secondCates = new JSONArray();
				if(children != null && children.size() > 0) {
					for(CategoryBean c : children){
						JSONObject secondObject = new JSONObject();
						secondObject.put("name", c.getName());
						secondObject.put("cid", c.getCid());
						secondCates.add(secondObject);
					}
				}
				firstObject.put("children", secondCates);
				jsonArray.add(firstObject);
			}
			String jsonMenu = JSON.toJSONString(jsonArray);
			serviceCacheImpl.setCategoryCache(jsonMenu);
		} else {
			jsonArray = JSON.parseArray(cateStr);
		}
		bean.setCategory(jsonArray);
		return bean;
	}

	@Override
	public PageResult<RCategoryBean> getService(int parentId) {
		PageResult<RCategoryBean> result = new PageResult<RCategoryBean>();
		int count = serviceImpl.selectServiceCountByPid(parentId);
		if(count == 0) {
			return null;
		}
		List<CategoryBean> resList = serviceImpl.selectServiceByPid(parentId);
		List<RCategoryBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (CategoryBean bean : resList) {
				RCategoryBean rb = parse(bean);
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
	private RCategoryBean parse(CategoryBean bean) {
		RCategoryBean cate = new RCategoryBean();
		cate.setCid(bean.getCid());
		cate.setName(bean.getName());
		cate.setSort(bean.getSort());
		cate.setAdminId(bean.getAdminId());
		if(bean.getCreateTime() != null) {
			cate.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return cate;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean submitOrder(int cid, String name, String phoneNum, String content) {
		String uid = null;
		boolean flag = false;
		// 判断用户手机号是否存在
		UserBean user = userImpl.selectOneByPhone(phoneNum);
		if(user == null) {
			// 创建用户信息（来源线上）
			user = new UserBean();
			uid = AppTextUtil.getPrimaryKey();
			user.setUid(uid);
			user.setName(name);
			user.setPhoneNum(phoneNum);
			user.setIsAllot(0);
			user.setOrigin(1);// 线上
			user.setLevel(1);//有效
			flag = userImpl.insert(user) > 0;
			if(!flag) {
				throw new ServiceException("create_user_error");
			}
			
		} else {
			uid = user.getUid();
			user.setStatus(0);// 等待沟通
			flag = userImpl.updateStatus(user) > 0;
			if(!flag) {
				throw new ServiceException("create_user_log_error");
			}
		}
		UserLogBean log = new UserLogBean();
		log.setUid(uid);
		log.setContent(content);
		log.setType(99);// 线上提交新需求
		flag = userImpl.insertUserLog(log) > 0;
		if(!flag) {
			throw new ServiceException("create_user_log_error");
		}
		UserNewsBean news = new UserNewsBean();
		news.setUid(uid);
		news.setContent(content);
		news.setOrigin(1);// 线上
		flag = userImpl.insertUserNews(news) > 0;
		if(!flag) {
			throw new ServiceException("create_user_news_error");
		}
		return false;
	}

}
