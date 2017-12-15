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
import com.psp.sellcenter.controller.res.bean.RUserLogsBean;
import com.psp.sellcenter.model.SellerBean;
import com.psp.sellcenter.model.UserBean;
import com.psp.sellcenter.model.UserLogBean;
import com.psp.sellcenter.persist.dao.OrderDao;
import com.psp.sellcenter.persist.dao.SellerDao;
import com.psp.sellcenter.persist.dao.UserDao;
import com.psp.sellcenter.persist.dao.UserLogDao;
import com.psp.sellcenter.service.UserService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	UserDao userImpl;
	
	@Autowired
	UserLogDao userLogImpl;
	
	@Autowired
	OrderDao orderImpl;
	

	@Override
	public PageResult<RUserBean> getUsers2Seller(String sid, int page, int pageSize,
				int filteType, int stype, String key, int status) {
		PageResult<RUserBean> result = new PageResult<RUserBean>();
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}	
		int count = userImpl.selectUserCount2Seller(sid, filteType, stype, key, status);
		if(count == 0) {
			return null;
		}
		List<UserBean> resList = userImpl.selectUsers2Seller(page, pageSize, sid, filteType, stype, key, status);
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
		if(user.getCreateTime() != null) {
			res.setCreateTime(user.getCreateTime().getTime() / 1000);
		}
		res.setIsAllot(user.getIsAllot());
		res.setLabel(user.getLabel());
		res.setLevel(user.getLevel());
		res.setOrderNum(user.getOrderNum());
		res.setOrigin(user.getOrigin());
		res.setPhoneNum(user.getPhoneNum());
		res.setPosition(user.getPosition());
		res.setCreaterJson(user.getSellerJson());
		if(user.getSeller() != null) {
			SellerBean selBean = user.getSeller();
			JSONObject sellerJson = new JSONObject();
			sellerJson.put("name", selBean.getUsername());
			sellerJson.put("phone", selBean.getPhoneNum());
			res.setSellerJson(sellerJson.toJSONString());
		}
		res.setSid(user.getSid());
		res.setUid(user.getUid());
		res.setStatus(user.getStatus());
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
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
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
			user.setSellerJson(sellerJson.toJSONString());
			user.setIsAllot(1);
			user.setLabel(label);
			user.setOrigin(2);// 线下
			user.setLevel(1);//有效
			user.setType(seller.getType() == 0 ? 0 : 1);
			logger.info("新建用户："+JSON.toJSONString(user));
			flag = userImpl.insert(user) > 0;
			if(!flag) {
				throw new ServiceException("create_user_error");
			}
			flag = insertUserLog(1, sid, seller.getUsername(), sellerJson.toJSONString(), user, null, null, null);
			if(!flag) {
				throw new ServiceException("create_user_log_error");
			}
			return parse(userImpl.selectUserByPhone(phoneNum));
		} else { // 当用户已存在
			String userInfo = "客户姓名："+ user.getName();
			userInfo += "，电话："+ user.getPhoneNum();
			userInfo += "，公司："+ user.getCompanyName();
			userInfo += "，职称："+ user.getPosition();
			if(user.getIsAllot() != 1) { // 用户未分配
				if(isClaim != 1) { //如果不主动认领
					throw new ServiceException("user_not_claim", userInfo);
				}
				// 如果主动认领,更新用户seller为当前用户
				user.setSid(sid);
				user.setSellerJson(sellerJson.toJSONString());
				flag = userImpl.updateSeller(user) > 0;
				if(!flag) {
					throw new ServiceException("update_user_seller_error");
				}
				flag = insertUserLog(4, sid, seller.getUsername(), sellerJson.toJSONString(), user, null, null, null);
				if(!flag) {
					throw new ServiceException("create_user_log_error");
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
					flag = userImpl.update(user) > 0;
					if(!flag) {
						throw new ServiceException("update_user_error");
					}
					flag = insertUserLog(2, sid, seller.getUsername(), sellerJson.toJSONString(), user, null, null, null);
					if(!flag) {
						throw new ServiceException("create_user_log_error");
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RUserBean eidtUser(String sid, String name, String phoneNum, String companyName, String position,
			String label, String uid) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		// 根据手机号判断用户是否存在
		UserBean user = userImpl.selectUserByPhone(phoneNum);
		if(user != null && !user.getUid().equals(uid)) {
			throw new ServiceException("object_is_exist", "该手机号注册客户");
		}
		user.setName(name);
		user.setPhoneNum(phoneNum);
		user.setCompanyName(companyName);
		user.setPosition(position);
		if(!StringUtil.isEmpty(label)) {
			user.setLabel(label);
		}
		flag = userImpl.update(user) > 0;
		if(!flag) {
			throw new ServiceException("update_user_error");
		}
		flag = insertUserLog(2, sid, seller.getUsername(), sellerJson.toJSONString(), user, null, null, null);
		if(!flag) {
			throw new ServiceException("create_user_log_error");
		}
		return parse(userImpl.selectUserByPhone(phoneNum));
	}

	@Override
	public int getUserNum2Seller(String sid, int status) {
		//TODO:存入缓存 客户数量存入缓存
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}	
		int count = userImpl.selectUserCount2Seller(sid, 0, 0, null, status);
		return count;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean eidtUserLevel(String sid, int level, String uid) {
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
			throw new ServiceException("object_is_not_exist", "设置客户");
		}
		if(user.getIsAllot() == 0 || !sid.equals(user.getSid())) {
			throw new ServiceException("seller_has_no_auth");
		}
		flag = userImpl.updateLevel(uid, level) > 0;
		if(!flag) {
			throw new ServiceException("update_user_error");
		}
		flag = insertUserLog(sid, seller.getUsername(), sellerJson.toJSONString(), user, level, null,  4);
		if(!flag) {
			throw new ServiceException("create_user_log_error");
		}
		return flag;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean eidtUserLabel(String sid, String label, String uid) {
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
			throw new ServiceException("object_is_not_exist", "设置客户");
		}
		if(user.getIsAllot() == 0 || !sid.equals(user.getSid())) {
			throw new ServiceException("seller_has_no_auth");
		}
		flag = userImpl.updateLabel(uid, label) > 0;
		if(!flag) {
			throw new ServiceException("update_user_error");
		}
		flag = insertUserLog(sid, seller.getUsername(), sellerJson.toJSONString(), user, user.getLevel(), label, 5);
		if(!flag) {
			throw new ServiceException("create_user_log_error");
		}
		return flag;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean archive(String sid, String uid) {
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
//		int userOrders = orderImpl.selectStageCount2User(uid, 1);
//		if(userOrders > 0) {
//			throw new ServiceException("can_no_archive");
//		}
		if(user.getIsAllot() == 0 || !sid.equals(user.getSid())) {
			throw new ServiceException("seller_has_no_auth");
		}
		flag = userImpl.archive(uid) > 0;
		if(!flag) {
			throw new ServiceException("update_user_error");
		}
		flag = insertUserLog(3, sid, seller.getUsername(), sellerJson.toJSONString(), user, null, null, null);
		if(!flag) {
			throw new ServiceException("create_user_log_error");
		}
		return flag;
	}
	
	@Override
	public RUserBean getDetail(String sid, String uid) {
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		UserBean user = userImpl.selectUserById(uid);
		if(user == null) {
			throw new ServiceException("object_is_not_exist", "客户");
		}
		return parse(user);
	}
	
	/**
	 * 获取客户操作日志
	 */
	@Override
	public PageResult<RUserLogsBean> getUserLogs(String sid, String uid, String key) {
		PageResult<RUserLogsBean> result = new PageResult<RUserLogsBean>();
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}	
		int count = userLogImpl.selectUserLogsCount(uid, key);
		if(count == 0) {
			return null;
		}
		List<UserLogBean> resList = userLogImpl.selectUserLogs(uid, key);
		List<RUserLogsBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (UserLogBean bean : resList) {
				RUserLogsBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}
	
	/**
	 * 插入客户操作日志
	 * @param type 操作类型
	 * @param sid 销售id
	 * @param sellerName 销售名称
	 * @param sellerJson 销售json
	 * @param user 用户信息
	 * @param aid 管理员ID
	 * @param adminName 管理员名称
	 * @param adminJson 管理员json
	 * @return
	 */
	private boolean insertUserLog(int type, String sid, String sellerName, String sellerJson, 
			UserBean user, String aid, String adminName, String adminJson) {
		UserLogBean userlog = new UserLogBean();
		userlog.setUid(user.getUid());
		if(!StringUtil.isEmpty(sid)) {
			userlog.setSid(sid);
			userlog.setSellerJson(sellerJson);
		}
		if(!StringUtil.isEmpty(aid)) {
			userlog.setAid(aid);
			userlog.setAdminJson(adminJson);
		}
		// 0管理员分配 1 新建客户 2 修改客户 3归档客户
		JSONObject userJson = new JSONObject();
		userJson.put("name", user.getName());
		userJson.put("phoneNum", user.getPhoneNum());
		userJson.put("companyName", user.getCompanyName());
		userJson.put("position", user.getPosition());
		userlog.setContent(userJson.toJSONString());
		userlog.setType(type);
		return userLogImpl.insert(userlog) > 0;
		
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
	private boolean insertUserLog(String sid, String username, String sellerJson, UserBean user, int level, String label, int type) {
		UserLogBean userlog = new UserLogBean();
		userlog.setUid(user.getUid());
		if(!StringUtil.isEmpty(sid)) {
			userlog.setSid(sid);
			userlog.setSellerJson(sellerJson);
		}
		// 4 设置评级 5 设置标签
		JSONObject userJson = new JSONObject();
		userJson.put("name", user.getName());
		userJson.put("level", level);
		userJson.put("label", label);
		userlog.setContent(userJson.toJSONString());
		userlog.setType(type);
		return userLogImpl.insert(userlog) > 0;
	}

	
	/**
	 * 格式化客户操作日志
	 * @param bean
	 * @return
	 */
	private RUserLogsBean parse(UserLogBean bean) {
		RUserLogsBean userlog = new RUserLogsBean();
		userlog.setUid(bean.getUid());
		userlog.setSid(bean.getSid());
		userlog.setSellerJson(bean.getSellerJson());
		userlog.setLid(bean.getLid());
		userlog.setAid(bean.getAid());
		userlog.setAdminJson(bean.getAdminJson());
		userlog.setContent(bean.getContent());
		userlog.setType(bean.getType());
		if(bean.getCreateTime() != null) {
			userlog.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return userlog;
	}


}
