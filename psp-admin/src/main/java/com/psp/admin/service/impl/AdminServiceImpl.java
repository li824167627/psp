package com.psp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psp.admin.cache.dao.AdminCacheDao;
import com.psp.admin.constants.StringConstants;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.controller.res.bean.ROrderStatisticsBean;
import com.psp.admin.controller.res.bean.RUserLevelStatisticsBean;
import com.psp.admin.controller.res.bean.RUserOnlineStatisticsBean;
import com.psp.admin.controller.res.bean.RUserStatisticsBean;
import com.psp.admin.controller.res.bean.RUserStatusStatisticsBean;
import com.psp.admin.model.AdminBean;
import com.psp.admin.model.Code;
import com.psp.admin.model.OrderStageStatisticsBean;
import com.psp.admin.model.OrderStatusStatisticsBean;
import com.psp.admin.model.UserLevelStatisticsBean;
import com.psp.admin.model.UserOnlineStatisticsBean;
import com.psp.admin.model.UserStatusStatisticsBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.persist.dao.OrderDao;
import com.psp.admin.persist.dao.UserDao;
import com.psp.admin.service.AdminService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.MD5Util;
import com.psp.util.NumUtil;
import com.psp.util.RandomUtil;
import com.psp.util.StringUtil;
import com.psp.util.VCodeSender;

@Service
public class AdminServiceImpl implements AdminService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AdminDao adminImpl;
	
	@Autowired
	AdminCacheDao adminCacheImpl;
	
	@Autowired
	OrderDao orderImpl;

	@Autowired
	UserDao userImpl;
	
	
	// 发送手机验证码
	VCodeSender phoneCode = VCodeSender.getInstance("N1330628", "t7NYh90uB");

	
	@Override
	public AdminBean getAdminByToken(String token) {
		if (StringUtil.isEmpty(token)) {
			return adminImpl.selectOneById("b70d85d1661a45d1b2648c46c7377db2");
		}
		String sid = adminCacheImpl.getAdminIdByToken(token);
		if (sid == null) {
			return null;
		}
		AdminBean user = adminImpl.selectOneById(sid);
		if(user.getStatus() != 0) {
			throw new ServiceException("account_is_forzen");
		}
		return user;
	}

	@Override
	public AdminBean getAdminById(String aid) {
		AdminBean user = adminImpl.selectOneById(aid);
		if(user.getStatus() != 0) {
			throw new ServiceException("account_is_forzen");
		}
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RAdminBean updateName(String adminId, String name) {
		boolean flag = false;
		AdminBean user = adminImpl.selectOneById(adminId);
		user.setUsername(name);
		flag = adminImpl.update(user) > 0;
		if(!flag) {
			throw new ServiceException("update_adimin_error");
		}
		return parse(user);
	}

	@Override
	public PageResult<RAdminBean> getList(String adminId, int page, int pageSize, String key) {
		PageResult<RAdminBean> result = new PageResult<RAdminBean>();
		
		int count = adminImpl.selectAdminCount(key);
		if(count == 0) {
			return null;
		}
		List<AdminBean> resList = adminImpl.selectAdmins(page, pageSize, key);
		List<RAdminBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (AdminBean bean : resList) {
				RAdminBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}
	
	private RAdminBean parse(AdminBean user) {
		RAdminBean admin = new RAdminBean();
		admin.setAid(user.getAid());
		if(user.getCreateTime() != null) {
			admin.setCreateTime(user.getCreateTime().getTime() / 1000);
		}
		if(user.getLastLoginTime() != null) {
			admin.setLastLoginTime(user.getLastLoginTime().getTime() / 1000);
		}
		admin.setPhoneNum(user.getPhoneNum());
		admin.setLetter(StringUtil.getFirstLetter(user.getUsername()));
		admin.setStatus(user.getStatus());
		admin.setType(user.getType());
		admin.setUsername(user.getUsername());
		admin.setPark(user.getPark());
		admin.setPid(user.getPid());
		return admin;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delAdmin(String adminId, String aid) {
		boolean flag = false;
		AdminBean admin  = adminImpl.selectOneById(aid);
		if(admin == null) {
			throw new ServiceException("object_is_not_exist", "运营账号");
		}
		//account.setPassword(MD5Util.md5("000000"));
		admin.setStatus(1);// 禁用
		flag = adminImpl.update(admin) > 0;
		if(!flag) {
			throw new ServiceException("update_adimin_error");
		}
		return flag;
	}

	@Override
	public boolean resetAdminPwd(String adminId, String aid) {
		boolean flag = false;
		AdminBean admin  = adminImpl.selectOneById(aid);
		if(admin == null) {
			throw new ServiceException("object_is_not_exist", "运营账号");
		}
		admin.setPassword(MD5Util.md5("000000"));
		flag = adminImpl.update(admin) > 0;
		if(!flag) {
			throw new ServiceException("update_provider_account_error");
		}
		return flag;
	}

	@Override
	public boolean editAdmin(String adminId, String aid, String pid, String name, String password, String confirmPwd,
			String phone, int type) {
		
		boolean flag = false;
		
		if(StringUtil.isEmpty(aid)) {
			AdminBean admin = adminImpl.selectOneByPhone(phone);
			if(admin != null) {// 新建
				throw new ServiceException("object_is_exist", "绑定手机号");
			}
			if(password == null || !password.equals(confirmPwd)) {
				throw new ServiceException("admin_pwd_error");
			}
			admin = new AdminBean();
			admin.setAid(AppTextUtil.getPrimaryKey());
			admin.setPhoneNum(phone);
			admin.setUsername(name);
			admin.setStatus(0);
			admin.setType(type);
			admin.setPid(pid);
			admin.setPassword(MD5Util.md5(password));
			flag = adminImpl.insert(admin) > 0;
			if(!flag) {
				throw new ServiceException("add_admin_error");
			}
		} else {
			AdminBean admin = adminImpl.selectOneById(aid);
			if(admin == null) {// 编辑
				throw new ServiceException("object_is_not_exist", "销售");
			}
			
			AdminBean phoneadmin = adminImpl.selectOneByPhone(phone);
			if(phoneadmin != null && !aid.equals(phoneadmin.getAid())) {// 编辑
				throw new ServiceException("object_is_exist", "绑定手机号");
			}
			admin.setPhoneNum(phone);
			admin.setUsername(name);
			admin.setType(type);
			flag = adminImpl.update(admin) > 0;
			if(!flag) {
				throw new ServiceException("update_seller_error");
			}
			
		}
		return flag;
	}

	@Override
	public void checkImgCode(String userName, String imgcode) {
		Code imgCode = adminCacheImpl.getImgCode(userName);
		logger.info("the imgcode is : " + imgCode);
		if (imgCode == null) {
			throw new ServiceException("imgcode_is_expire");
		}
		imgcode = imgcode.toUpperCase();
		if ((StringUtil.isEmpty(imgcode)) || (!imgcode.equals(imgCode.getCode()))) {
			logger.info("set imgcode is : " + imgCode);
			throw new ServiceException("imgcode_is_error");
		}
	}

	@Override
	public boolean sendVCode(int type, String phone) {
		AdminBean user = adminImpl.selectOneByPhone(phone);
		if (type == 3 && user == null) { // 1 更新手机号2 找回密码3 重置个人密码
			throw new ServiceException("object_is_not_exist", "手机号");
		} else if ((type == 1 | type == 2) && user != null) {
			throw new ServiceException("object_is_exist", "手机号");
		}
		long now = System.currentTimeMillis() / 1000;
		logger.info("now is : " + now);

		Code c = adminCacheImpl.getVCode(type, phone);
		logger.info("vcode is : " + c);
		if (c != null) {
			long passTime = now - c.getTime();
			logger.info("passTime is : " + passTime);
			if (passTime < 60) {
				throw new ServiceException("vcode_in_limit");
			}
		}
		String vcode = RandomUtil.getRandomNum(4);
		logger.info("the phone vcode is : " + vcode);
		Code cacheCode = new Code();
		cacheCode.setCode(vcode);
		cacheCode.setTime(System.currentTimeMillis() / 1000);
		adminCacheImpl.setVCode(type, phone, cacheCode);

		String msg = StringConstants.getInstance().getString("msg_vcode_send",  vcode, 5);
		phoneCode.send(phone, msg, null);
		return true;
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public RAdminBean login(String sessionId, String phone, String pwd, String vcode, String device, String ip) {

		AdminBean user = adminImpl.selectOneByPhone(phone);
		if (user == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
//		Code code = adminCacheImpl.getLoginCode(phone);
//		if (code == null) {
//			code = new Code();
//			code.setNum(0);
//			adminCacheImpl.setLoginCode(phone, code);
//		} else if (code.getNum() > 4) {
//			if (StringUtil.isEmpty(code.getCode())) {
//				throw new ServiceException("imgcode_is_cross");
//			}
//			if (!code.getCode().equals(vcode.toUpperCase())) {
//				throw new ServiceException("imgcode_is_error");
//			}
//		}

		pwd = MD5Util.md5(pwd);
		if (!pwd.equals(user.getPassword())) {
//			code.setNum(code.getNum() + 1);
//			adminCacheImpl.setLoginCode(phone, code);
			throw new ServiceException("user_password_is_error");
		}

		if (NumUtil.toInt(user.getStatus(), 0) == 1) {
			throw new ServiceException("account_is_forzen");
		}

		adminCacheImpl.setAdminIdTOKEN(sessionId, user.getAid(), 24*60*60L);
		boolean flag = adminImpl.updateLoginTime(user.getAid()) > 0;
		if (!flag) {
			throw new ServiceException("user_update_error");
		}
//		if (flag) {
//			logger.info("sessionId= " + sessionId);
//			UserLoginLogBean loginLog = new UserLoginLogBean();
//			loginLog.setDevice(device);
//			loginLog.setLoginIp(ip);
//			loginLog.setUid(user.getAid());
//			loginLog.setOstate(1);// 1 登录 2注册
//			flag = adminImpl.insertLoginLog(loginLog) > 0;
//			if (!flag) {
//				throw new ServiceException("user_log_insert_error");
//			}
//		}

		return parse(user);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updatePassWord(String adminId, String pwd, String newPwd, String subPwd) {
		AdminBean admin = adminImpl.selectOneById(adminId);
		if (admin == null) {
			throw new ServiceException("object_is_not_exist", "用户");
		}
		if (!MD5Util.md5(pwd).equals(admin.getPassword())) {
			throw new ServiceException("user_password_is_error");
		}
		
		if (newPwd != null && !newPwd.equals(subPwd)) {
			throw new ServiceException("user_password_is_not_same");
		}
		
		admin.setPassword(MD5Util.md5(newPwd));
		boolean flag = adminImpl.update(admin) > 0;
		if(!flag) {
			throw new ServiceException("update_seller_error");
		}
		return flag;
	}

	@Override
	public ROrderStatisticsBean getOrderStatistics(String adminId) { 
		AdminBean admin = adminImpl.selectOneById(adminId);
		String parkId = null;
		if(admin.getType() == 0) {
			parkId = admin.getPid();
		}
		OrderStatusStatisticsBean orderStatus = orderImpl.selectOrderStatusCount(parkId);
		OrderStageStatisticsBean orderStage = orderImpl.selectOrderStagesCount(parkId);
		
		logger.info(JSON.toJSONString(orderStage));
		JSONArray status = new JSONArray();
		
		JSONObject obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getCompleted(), 0));
		obj.put("name", "已完成");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getToAllot(), 0));
		obj.put("name", "待分配");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getPending(), 0));
		obj.put("name", "待处理");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getAccept(), 0));
		obj.put("name", "已接收");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getToContract(), 0));
		obj.put("name", "合同已上传");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getToApplyCompelete(), 0));
		obj.put("name", "申请完成");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getToFeedback(), 0));
		obj.put("name", "待反馈");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getRefuse(), 0));
		obj.put("name", "拒绝完成");
		status.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getToApplyFinished(), 0));
		obj.put("name", "申请终止");
		status.add(obj);
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStatus.getClosed(), 0));
		obj.put("name", "已关闭");
		status.add(obj);
		
		JSONArray stage = new JSONArray();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStage.getCompleted(), 0));
		obj.put("name", "已完成");
		stage.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStage.getUnderway(), 0));
		obj.put("name", "进行中");
		stage.add(obj);obj = new JSONObject();
		
		obj = new JSONObject();
		obj.put("value", NumUtil.toInt(orderStage.getClosed(), 0));
		obj.put("name", "已关闭");
		stage.add(obj);obj = new JSONObject();
		ROrderStatisticsBean order = new ROrderStatisticsBean();
		
		order.setStage(stage);
		order.setStatus(status);
		return order;
	}

	@Override
	public RUserStatisticsBean getUserStatistics(String adminId) {
		AdminBean admin = adminImpl.selectOneById(adminId);
		String parkId = null;
		if(admin.getType() == 0) {
			parkId = admin.getPid();
		}
		UserLevelStatisticsBean level = userImpl.selectLevelCount(parkId);
		UserStatusStatisticsBean status = userImpl.selectStatusCount(parkId);
		UserOnlineStatisticsBean online = userImpl.selectOnlineCount(parkId);
		RUserLevelStatisticsBean rlevel = new RUserLevelStatisticsBean();
		rlevel.setUnrated(level.getUnrated());
		rlevel.setValid(level.getValid());
		rlevel.setUnvalid(level.getUnvalid());
		RUserStatusStatisticsBean rstatus = new RUserStatusStatisticsBean();
		rstatus.setCommunicate(status.getCommunicate());
		rstatus.setUncommunicate(status.getUncommunicate());
		RUserOnlineStatisticsBean ronline = new RUserOnlineStatisticsBean();
		ronline.setOffline(online.getOffline());
		ronline.setOnline(online.getOnline());
		RUserStatisticsBean user = new RUserStatisticsBean();
		user.setLevel(rlevel);
		user.setStatus(rstatus);
		user.setOnline(ronline);
		return user;
	}
}
                                                                                     