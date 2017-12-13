package com.psp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.psp.admin.cache.dao.ServiceCacheDao;
import com.psp.admin.controller.res.bean.RAccountBean;
import com.psp.admin.controller.res.bean.RProviderBean;
import com.psp.admin.model.AccountBean;
import com.psp.admin.model.CategoryBean;
import com.psp.admin.model.ProviderBean;
import com.psp.admin.model.ProviderServiceBean;
import com.psp.admin.persist.dao.ProviderDao;
import com.psp.admin.persist.dao.ServiceDao;
import com.psp.admin.service.ProviderService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.MD5Util;

@Service
public class ProviderServiceImpl implements ProviderService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ProviderDao providerImpl;
	
	@Autowired
	ServiceDao serviceImpl;
	
	@Autowired
	ServiceCacheDao serviceCacheImpl;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RAccountBean addProvider(String name, String address, String contact, String phoneNum,
			String content, String password, String confirmPwd, String adminId) {
		boolean flag = false;
		ProviderBean provider = new ProviderBean();
		String pid = AppTextUtil.getPrimaryKey();
		provider.setPid(pid);
		provider.setName(name);
		provider.setAddress(address);
		provider.setContact(contact);
		provider.setContent(content);
		provider.setPhoneNum(phoneNum);
		provider.setStatus(0);
		provider.setAid(adminId);
		flag = providerImpl.insert(provider) > 0;
		if(!flag) {
			throw new ServiceException("add_provider_error");
		}
		// 创建 服务商可操作服务
		AccountBean account = providerImpl.selectAccountByPhone(phoneNum);
		if(account != null) {
			throw new ServiceException("object_is_exist", "当前手机账号");
		}
		
		account = new AccountBean();
		String aid = AppTextUtil.getPrimaryKey();
		account.setAid(aid);
		account.setUsername(contact);
		account.setPid(pid);
		account.setPhoneNum(phoneNum);
		account.setType(1);//0 员工 1 服务商管理员
		account.setStatus(0);// 0 正常
		if(!password.equals(confirmPwd)) {
			throw new ServiceException("provider_password_not_same");
		}
		
		// TODO：验证密码
		account.setPassword(MD5Util.md5(password));
		flag = providerImpl.insertAccount(account) > 0;
		if(!flag) {
			throw new ServiceException("add_provider_account_error");
		}
		return parse(providerImpl.selectAccountById(aid));
	}
	
	/**
	 * 服务商账号类型转化
	 * @param account
	 * @return
	 */
	private RAccountBean parse(AccountBean account) {
		RAccountBean res = new RAccountBean();
		res.setAid(account.getAid());
		res.setUsername(account.getUsername());
		res.setPhoneNum(account.getPhoneNum());
		res.setStatus(account.getStatus());
		res.setType(account.getType());
		if(account.getCreateTime() != null) {
			res.setCreateTime(account.getCreateTime().getTime() / 1000);
		}
		if(account.getLastLoginTime() != null) {
			res.setLastLoginTime(account.getLastLoginTime().getTime() / 1000);
		}
		return res;
	}

	@Override
	public PageResult<RProviderBean> getList(String adminId, int page, int pageSize, int cid) {
		PageResult<RProviderBean> result = new PageResult<RProviderBean>();
		if(cid != 0) { // TODO: 根据分类筛选
			CategoryBean cate =  serviceImpl.selectServiceById(cid);
			if(cate == null) {
				throw new ServiceException("object_is_not_exist", "所选分类");
			}
		}
		
		int count = providerImpl.selectProviderCount(cid);
		if(count == 0) {
			return null;
		}
		List<ProviderBean> resList = providerImpl.selectProviders(page, pageSize, cid);
		List<RProviderBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (ProviderBean bean : resList) {
				RProviderBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}
	
	private RProviderBean parse(ProviderBean bean) {
		RProviderBean provider = new RProviderBean();
		provider.setPid(bean.getPid());
		provider.setAddress(bean.getAddress());
		provider.setContact(bean.getContact());
		provider.setContent(bean.getContent());
		provider.setName(bean.getName());
		provider.setPhoneNum(bean.getPhoneNum());
		provider.setScore(bean.getScore());
		provider.setStatus(bean.getStatus());
		provider.setAdmin(bean.getAdmin());
		if(bean.getCreateTime() != null) {
			provider.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return provider;
	}

	@Override
	public RProviderBean getDetail(String adminId, String pid) {
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider == null) {
			throw new ServiceException("object_is_not_exist", "服务商");
		}
		return parse(provider);
	}

	@Override
	public PageResult<RAccountBean> getAccountList(String adminId, int page, int pageSize, String pid) {
		PageResult<RAccountBean> result = new PageResult<RAccountBean>();
		int count = providerImpl.selectAccountCount(pid);
		if(count == 0) {
			return null;
		}
		List<AccountBean> resList = providerImpl.selectAccounts(page, pageSize, pid);
		List<RAccountBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (AccountBean bean : resList) {
				RAccountBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addAccount(String adminId, String name, String phone, String password, String pid) {
		boolean flag = false;
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider == null) {
			throw new ServiceException("object_is_not_exist", "服务商");
		}
		AccountBean account = providerImpl.selectAccountByPhone(phone);
		if(account != null) {
			throw new ServiceException("object_is_exist", "当前手机账号");
		}
		
		account = new AccountBean();
		account.setPid(pid);
		account.setAid(AppTextUtil.getPrimaryKey());
		account.setUsername(name);
		account.setPhoneNum(phone);
		account.setType(0);//0 员工 1 服务商管理员
		account.setStatus(0);// 0 正常
		account.setPassword(MD5Util.md5(password));
		flag = providerImpl.insertAccount(account) > 0;
		if(!flag) {
			throw new ServiceException("add_provider_account_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean resetPwd(String adminId, String aid) {
		boolean flag = false;
		AccountBean account = providerImpl.selectAccountById(aid);
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "服务商账号");
		}
		account.setPassword(MD5Util.md5("000000"));
		flag = providerImpl.updateAccount(account) > 0;
		if(!flag) {
			throw new ServiceException("update_provider_account_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delAccount(String adminId, String aid) {
		boolean flag = false;
		AccountBean account = providerImpl.selectAccountById(aid);
		if(account == null) {
			throw new ServiceException("object_is_not_exist", "服务商账号");
		}
		//account.setPassword(MD5Util.md5("000000"));
		account.setStatus(1);// 禁用
		flag = providerImpl.updateAccount(account) > 0;
		if(!flag) {
			throw new ServiceException("update_provider_account_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean editProvider(String pid, String name, String address, String contact, String phoneNum, String content) {
		boolean flag = false;
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider != null) {
			throw new ServiceException("object_is_not_exist", "服务商");
		}
		provider = new ProviderBean();
		provider.setName(name);
		provider.setAddress(address);
		provider.setContact(contact);
		provider.setContent(content);
		provider.setPhoneNum(phoneNum);
		flag = providerImpl.update(provider) > 0;
		if(!flag) {
			throw new ServiceException("update_provider_error");
		}
		AccountBean account = providerImpl.selectAccountByPhone(phoneNum);
		if(account == null) {
			account = new AccountBean();
			String aid = AppTextUtil.getPrimaryKey();
			account.setAid(aid);
			account.setUsername(contact);
			account.setPid(pid);
			account.setPhoneNum(phoneNum);
			account.setType(1);//0 员工 1 服务商管理员
			account.setStatus(0);// 0 正常
			account.setPassword(MD5Util.md5("000000"));
			flag = providerImpl.insertAccount(account) > 0;
			if(!flag) {
				throw new ServiceException("add_provider_account_error");
			}
		}
		
		return flag;
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addService(String pid, String cid) {
		boolean flag = false;
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider == null) {
			throw new ServiceException("object_is_not_exist", "服务商");
		}
		
		ProviderServiceBean ps = providerImpl.selectServiceByPidCid(pid, cid);
		if(ps != null) {
			throw new ServiceException("object_is_exist", "服务商提供的服务");
		}
		flag = providerImpl.addService(pid, cid) > 0;
		if(!flag) {
			throw new ServiceException("add_provider_service_error");
		}
		
		// 清除服务分类缓存
		flag = serviceCacheImpl.setAllCategoryCache(null);
		flag = serviceCacheImpl.setCategoryCache(null);
		flag = serviceCacheImpl.setSellerCategoryCache(null);
				
		return flag;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delService(String pid, String cid) {
		boolean flag = false;
		ProviderBean provider = providerImpl.selectOneById(pid);
		if(provider == null) {
			throw new ServiceException("object_is_not_exist", "服务商");
		}
		flag = providerImpl.delService(pid, cid) > 0;
		if(!flag) {
			throw new ServiceException("add_provider_service_error");
		}
		
		// 清除服务分类缓存
		flag = serviceCacheImpl.setAllCategoryCache(null);
		flag = serviceCacheImpl.setCategoryCache(null);
		flag = serviceCacheImpl.setSellerCategoryCache(null);
		return flag;
	}

	

}
