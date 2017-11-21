package com.psp.sellcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psp.sellcenter.controller.res.bean.RUserBean;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.model.UserBean;
import com.psp.sellcenter.persist.dao.SellerDao;
import com.psp.sellcenter.persist.dao.UserDao;
import com.psp.sellcenter.service.UserService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
import com.psp.util.AppTextUtil;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	UserDao userImpl;
	

	@Override
	public PageResult<RUserBean> getUsers2Seller(String sid, int page, int pageSize, int filteType, int stype, String key) {
		PageResult<RUserBean> result = new PageResult<RUserBean>();
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}	
		int count = userImpl.selectUserCount2Seller(sid, filteType, stype, key);
		if(count == 0) {
			return null;
		}
		List<UserBean> resList = userImpl.selectUsers2Seller(page, pageSize, sid, filteType, stype, key);
		List<RUserBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (UserBean bean : resList) {
				RUserBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}	
	
	/**
	 * 格式化数据
	 * @param user
	 * @return
	 */
	private RUserBean parse(UserBean user) {
		RUserBean res = new RUserBean();
		res.setName(user.getName());
		res.setAdminJson(user.getAdminJson());
		res.setAid(user.getAid());
		if(user.getAllotTime() != null) {
			res.setAllotTime(user.getAllotTime().getTime() / 1000);
		}
		res.setCompanyName(user.getCompanyName());
		res.setCreateTime(user.getCreateTime().getTime() / 1000);
		res.setIsAllot(user.getIsAllot());
		res.setLabel(user.getLabel());
		res.setLevel(user.getLevel());
		res.setOrderNum(user.getOrderNum());
		res.setOrigin(user.getOrigin());
		res.setPhoneNum(user.getPhoneNum());
		res.setPosition(user.getPosition());
		res.setSellerJson(user.getSellerJson());
		res.setSid(user.getSid());
		res.setUid(user.getUid());
		logger.info(JSON.toJSONString(res));
		return res;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RUserBean addUser(String sid, String name, String phoneNum, String companyName, String position,
			String label, int isUpdate, int isClaim) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		// 根据手机号判断用户是否存在
		UserBean user = userImpl.selectUserByPhone(phoneNum);
		if(user == null) { // 新建用户
			user = new UserBean();
			user.setUid(AppTextUtil.getPrimaryKey());
			user.setName(name);
			user.setPhoneNum(phoneNum);
			user.setCompanyName(companyName);
			user.setPosition(position);
			user.setLabel(label);
			user.setSid(sid);
			JSONObject sellerJson = new JSONObject();
			sellerJson.put("name", seller.getUsername());
			sellerJson.put("phone", seller.getPhoneNum());
			user.setSellerJson(sellerJson.toJSONString());
			user.setIsAllot(1);
			user.setLabel(label);
			user.setOrigin(2);// 线下
			user.setLevel(1);//有效
			logger.info("新建用户："+JSON.toJSONString(user));
			flag = userImpl.insert(user) > 0;//TODO：客户操作日志
			if(!flag) {
				throw new ServiceException("create_user_error");
			}
			return parse(userImpl.selectUserByPhone(phoneNum));
		} else { // 当用户已存在
			String userInfo = "客户姓名："+ user.getName();
			userInfo += "，电话："+ user.getPhoneNum();
			userInfo += "，公司："+ user.getCompanyName();
			if(user.getIsAllot() != 1) { // 用户未分配
				if(isClaim != 1) { //如果不主动认领
					throw new ServiceException("user_not_claim", userInfo);
				}
				// 如果主动认领,更新用户seller为当前用户
				user.setSid(sid);
				JSONObject sellerJson = new JSONObject();
				sellerJson.put("name", seller.getUsername());
				sellerJson.put("phone", seller.getPhoneNum());
				user.setSellerJson(sellerJson.toJSONString());
				flag = userImpl.updateSeller(user) > 0;//TODO：客户操作日志
				if(!flag) {
					throw new ServiceException("update_user_seller_error");
				}
				return parse(userImpl.selectUserByPhone(phoneNum));
			} else { // 用户已分配
				if(sid.equals(user.getSid())) {// 如果是当前销售管理的客户
					if(isUpdate != 1) {// 不更新客户信息，提示已认领
						throw new ServiceException("user_is_claimed_by_u", userInfo);
					}
					user.setName(name);
					user.setPhoneNum(phoneNum);
					user.setCompanyName(companyName);
					user.setPosition(position);
					user.setLabel(label);
					flag = userImpl.update(user) > 0;//TODO：客户操作日志
					if(!flag) {
						throw new ServiceException("update_user_error");
					}
					return parse(userImpl.selectUserByPhone(phoneNum));
				} else { // 不是当前销售管理的客户
					SellerBean hisSeller = sellerImpl.selectOneById(user.getSid());
					if(hisSeller == null) {
						throw new ServiceException("user_not_claim", userInfo);
					} else {
						String sellerInfo = "姓名："+ hisSeller.getUsername();
						sellerInfo += "，手机号："+ hisSeller.getPhoneNum();
						throw new ServiceException("user_is_claimed", sellerInfo);
					}
				}
					
			}
		}
	}

}
