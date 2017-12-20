package com.psp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.psp.admin.controller.res.bean.RSellerBean;
import com.psp.admin.model.AdminBean;
import com.psp.admin.model.SellerBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.persist.dao.SellerDao;
import com.psp.admin.service.SellerService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.MD5Util;
import com.psp.util.StringUtil;

@Service
public class SellerServiceImpl implements SellerService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	AdminDao adminImpl;

	@Override
	public PageResult<RSellerBean> getSellers(int page, int pageSize, String pid, String key, String adminId) {
		PageResult<RSellerBean> result = new PageResult<RSellerBean>();
		AdminBean admin = adminImpl.selectOneById(adminId);
		String parkId = null;
		if(admin.getType() == 0) {
			parkId = admin.getPid();
		}
		int count = sellerImpl.selectSellerCount(pid, key, parkId);
		if(count == 0) {
			return null;
		}
		List<SellerBean> resList = sellerImpl.selectSellers(page, pageSize, pid, key, parkId);
		List<RSellerBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (SellerBean bean : resList) {
				RSellerBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}

	private RSellerBean parse(SellerBean bean) {
		RSellerBean seller = new RSellerBean();
		seller.setSid(bean.getSid());
		seller.setPhoneNum(bean.getPhoneNum());
		seller.setStatus(bean.getStatus());
		seller.setUsername(bean.getUsername());
		seller.setAdmin(bean.getAdmin());
		if(bean.getCreateTime() != null) {
			seller.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		if(bean.getLastLoginTime() != null) {
			seller.setLastLoginTime(bean.getLastLoginTime().getTime() / 1000);
		}
		seller.setParkName(bean.getParkName());
		seller.setPid(bean.getPid());
		seller.setType(bean.getType());
		return seller;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean EditSeller(String adminId, String sid, String name, String password, String phoneNum, String pid, int type) {
		boolean flag = false;
		if(StringUtil.isEmpty(sid)) {
			SellerBean seller = sellerImpl.selectOneByPhone(phoneNum);
			if(seller != null) {// 新建
				throw new ServiceException("object_is_exist", "绑定手机号");
			}
			seller = new SellerBean();
			seller.setPid(pid);
			seller.setSid(AppTextUtil.getPrimaryKey());
			seller.setPhoneNum(phoneNum);
			seller.setUsername(name);
			seller.setStatus(0);
			seller.setType(type);
			seller.setAid(adminId);
			seller.setPassword(MD5Util.md5(password));
			flag = sellerImpl.insert(seller) > 0;
			if(!flag) {
				throw new ServiceException("add_seller_error");
			}
		} else {
			SellerBean seller = sellerImpl.selectOneById(sid);
			if(seller == null) {// 编辑
				throw new ServiceException("object_is_not_exist", "销售");
			}
			
			SellerBean phoneseller = sellerImpl.selectOneByPhone(phoneNum);
			if(phoneseller != null && sid.equals(phoneseller.getAid())) {// 编辑
				throw new ServiceException("object_is_exist", "绑定手机号");
			}
			seller.setPhoneNum(phoneNum);
			seller.setUsername(name);
			seller.setPid(pid);
			seller.setType(type);
			if(!StringUtil.isEmpty(password)) {
				seller.setPassword(MD5Util.md5(password));
			}
			flag = sellerImpl.update(seller) > 0;
			if(!flag) {
				throw new ServiceException("update_seller_error");
			}
			
		}
		return flag;
	}

	@Override
	public boolean resetPwd(String adminId, String sid) {
		boolean flag = false;
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		seller.setPassword(MD5Util.md5("000000"));
		flag = sellerImpl.update(seller) > 0;
		if(!flag) {
			throw new ServiceException("update_seller_error");
		}
		return flag;
	}

	@Override
	public boolean delSeller(String adminId, String sid) {
		boolean flag = false;
		SellerBean seller = sellerImpl.selectOneById(sid);
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "销售");
		}
		seller.setStatus(1);// 禁用
		flag = sellerImpl.update(seller) > 0;
		if(!flag) {
			throw new ServiceException("update_seller_error");
		}
		return flag;
	}
	
	

}
