package com.psp.admin.service.impl;

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
import com.psp.admin.cache.dao.ServiceCacheDao;
import com.psp.admin.controller.res.bean.RCategoryBean;
import com.psp.admin.controller.res.bean.RCategoryJSONBean;
import com.psp.admin.model.CategoryBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.persist.dao.ServiceDao;
import com.psp.admin.service.CategoryService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.StringUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ServiceDao serviceImpl;
	
	@Autowired
	AdminDao adminImpl;
	
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
				logger.info("当前分类：" + JSON.toJSONString(cate));
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
				if(children != null && children.size() > 0) {
					JSONArray secondCates = new JSONArray();
					for(CategoryBean c : children){
						JSONObject secondObject = new JSONObject();
						secondObject.put("name", c.getName());
						secondObject.put("cid", c.getCid());
						secondObject.put("children", AllServices.get(c.getCid()));
						secondCates.add(secondObject);
					}
					firstObject.put("children", secondCates);
				}
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
				if(children != null && children.size() > 0) {
					JSONArray secondCates = new JSONArray();
					for(CategoryBean c : children){
						JSONObject secondObject = new JSONObject();
						secondObject.put("name", c.getName());
						secondObject.put("cid", c.getCid());
						secondCates.add(secondObject);
					}
					firstObject.put("children", secondCates);
				}
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
	public boolean addService(String aid, int parentId, String name, int sort, int isService) {
		boolean flag = false;
		CategoryBean cate = new CategoryBean();
		cate.setAdminId(aid);
		cate.setName(name);
		cate.setParentId(parentId);
		cate.setSort(sort);        
		cate.setIsService(isService);
		
		flag = serviceImpl.insert(cate) > 0;
		if(!flag) {
			throw new ServiceException("add_service_error");
		}
		
		// 清除服务分类缓存
		flag = serviceCacheImpl.setAllCategoryCache(null);
		flag = serviceCacheImpl.setCategoryCache(null);
		flag = serviceCacheImpl.setSellerCategoryCache(null);
		return flag;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean editService(String aid, int cid, int parentId, String name, int sort, int isService) {
		boolean flag = false;
		CategoryBean cate = serviceImpl.selectServiceById(cid);
		if(cate == null) {
			throw new ServiceException("object_is_not_exist", "服务");
		}
		cate.setAdminId(aid);
		cate.setName(name);
		cate.setParentId(parentId);
		cate.setSort(sort);        
		cate.setIsService(isService);
		
		flag = serviceImpl.update(cate) > 0;
		if(!flag) {
			throw new ServiceException("add_service_error");
		}
		
		// 清除服务分类缓存
		flag = serviceCacheImpl.setAllCategoryCache(null);
		flag = serviceCacheImpl.setCategoryCache(null);
		flag = serviceCacheImpl.setSellerCategoryCache(null);
		return flag;
	}
	

}
