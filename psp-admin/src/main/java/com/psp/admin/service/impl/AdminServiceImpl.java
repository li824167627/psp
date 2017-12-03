package com.psp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.psp.admin.cache.dao.AdminCacheDao;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.model.AdminBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.service.AdminService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.MD5Util;
import com.psp.util.StringUtil;

@Service
public class AdminServiceImpl implements AdminService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AdminDao adminImpl;
	
	@Autowired
	AdminCacheDao adminCacheImpl;
	
	@Override
	public AdminBean getAdminByToken(String token) {
		if (token == null) {
			return null;
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
	public boolean updateName(String adminId, String name) {
		AdminBean user = adminImpl.selectOneById(adminId);
		user.setUsername(name);
		return adminImpl.update(user) > 0;
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
		admin.setStatus(user.getStatus());
		admin.setType(user.getType());
		admin.setUsername(user.getUsername());
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
	public boolean resetPwd(String adminId, String aid) {
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
			if(phoneadmin != null) {// 编辑
				throw new ServiceException("object_is_exist", "绑定手机号");
			}
			admin.setPhoneNum(phone);
			admin.setUsername(name);
			flag = adminImpl.update(admin) > 0;
			if(!flag) {
				throw new ServiceException("update_seller_error");
			}
			
		}
		return flag;
	}

}
