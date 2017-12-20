package com.psp.sellcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psp.sellcenter.controller.res.bean.RUserNewsBean;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.model.UserBean;
import com.psp.sellcenter.model.UserLogBean;
import com.psp.sellcenter.model.UserNewsBean;
import com.psp.sellcenter.persist.dao.SellerDao;
import com.psp.sellcenter.persist.dao.UserDao;
import com.psp.sellcenter.persist.dao.UserLogDao;
import com.psp.sellcenter.persist.dao.UserNewsDao;
import com.psp.sellcenter.service.UserNewsService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
import com.psp.util.StringUtil;

@Service
public class UserNewsServiceImpl implements UserNewsService{

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserNewsDao userNewsImpl;
	
	@Autowired
	UserDao userImpl;
	
	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	UserLogDao userLogImpl;

	@Override
	public PageResult<RUserNewsBean> getUserNews(String sid, int page, int pageSize, int stype, String key, String uid) {
		PageResult<RUserNewsBean> result = new PageResult<RUserNewsBean>();
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}	
		int count = userNewsImpl.selectUserNewsCount(null, stype, key, uid);
		if(count == 0) {
			return null;
		}
		List<UserNewsBean> resList = userNewsImpl.selectUserNews(page, pageSize, null, stype, key, uid);
		List<RUserNewsBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (UserNewsBean bean : resList) {
				RUserNewsBean rb = parse(bean);
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
	private RUserNewsBean parse(UserNewsBean bean) {
		RUserNewsBean res = new RUserNewsBean();
		res.setContent(bean.getContent());
		if(bean.getCreateTime() != null) {
			res.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		res.setLabel(bean.getLabel());
		res.setNid(bean.getNid());
		res.setOrigin(bean.getOrigin());
		res.setUid(bean.getUid());
		res.setUserJson(bean.getUserJson());
		res.setSid(bean.getSid());
		res.setSellerJson(bean.getSellerJson());
		return res;
	}
	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean add(String sid, String uid, String label, String content) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		UserBean user = userImpl.selectUserById(uid);
		if(user == null) {
			throw new ServiceException("object_is_not_exist", "客户");
		}
		if(user.getIsAllot() == 0 || !sid.equals(user.getSid())) {
			throw new ServiceException("seller_has_no_auth");
		}
		UserNewsBean news = new UserNewsBean();
		news.setOrigin(2);// 来源：主动沟通
		news.setContent(content);
		news.setLabel(label);
		news.setSellerJson(sellerJson.toJSONString());
		news.setSid(sid);
		news.setUid(uid);
		JSONObject userJson = new JSONObject();
		userJson.put("name", user.getName());
		userJson.put("phoneNum", user.getPhoneNum());
		userJson.put("companyName", user.getCompanyName());
		userJson.put("position", user.getPosition());
		news.setUserJson(userJson.toJSONString());
		
		flag = userNewsImpl.insert(news) > 0;
		if(!flag) {
			throw new ServiceException("create_user_news_error");
		}
		// 更改用户状态为已沟通
		if(user.getStatus() != 1) {
			user.setStatus(1);
			flag = userImpl.updateStatus(user) > 0;
			if(!flag) {
				throw new ServiceException("update_user_error");
			}
			flag = insertUserLog(sid, seller.getUsername(), sellerJson.toJSONString(), user, 1,  6);
			if(!flag) {
				throw new ServiceException("create_user_log_error");
			}
		}
		return flag;
	}
	
	/**
	 * 插入客户更新日志
	 * @param sid
	 * @param username
	 * @param sellerJson
	 * @param user
	 * @param level
	 * @param label
	 * @param type
	 * @return
	 */
	private boolean insertUserLog(String sid, String username, String sellerJson, UserBean user, int status, int type) {
		UserLogBean userlog = new UserLogBean();
		userlog.setUid(user.getUid());
		if(!StringUtil.isEmpty(sid)) {
			userlog.setSid(sid);
			userlog.setSellerJson(sellerJson);
		}
		// 4 设置评级 5 设置标签 6 更新状态 开始沟通
		JSONObject userJson = new JSONObject();
		userJson.put("name", user.getName());
		userJson.put("status", status);
		userlog.setContent(userJson.toJSONString());
		userlog.setType(type);
		return userLogImpl.insert(userlog) > 0;
	}

	

}
