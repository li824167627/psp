package com.psp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.psp.admin.controller.res.bean.RParkBean;
import com.psp.admin.model.ParkBean;
import com.psp.admin.persist.dao.ParkDao;
import com.psp.admin.service.ParkService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.StringUtil;

@Service
public class ParkServiceImpl implements ParkService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ParkDao parkImpl;

	@Override
	public PageResult<RParkBean> getList(String adminId, int page, int pageSize, String key) {
		PageResult<RParkBean> result = new PageResult<RParkBean>();
		int count = parkImpl.selectCount(key);
		if(count == 0) {
			return null;
		}
		List<ParkBean> resList = parkImpl.selectList(page, pageSize, key);
		List<RParkBean> resData = new ArrayList<>();
		logger.info(JSON.toJSONString(resList));
		if (resList != null && resList.size() > 0) {
			for (ParkBean bean : resList) {
				RParkBean rb = parse(bean);
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
	private RParkBean parse(ParkBean bean) {
		RParkBean park = new RParkBean();
		park.setAdminId(bean.getAdminId());
		park.setBrief(bean.getBrief());
		park.setCity(bean.getCity());
		park.setCityCode(bean.getCityCode());
		park.setContact(bean.getContact());
		park.setCoordinate(bean.getCoordinate());
		park.setDistrict(bean.getDistrict());
		park.setName(bean.getName());
		park.setPhoneNum(bean.getPhoneNum());
		park.setPid(bean.getPid());
		park.setProvince(bean.getProvince());
		park.setStatus(bean.getStatus());
		if(bean.getCreateTime() != null) {
			park.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return park;
	}

	@Override
	public boolean eidtPark(String adminId, String name, String pid, String contact, String phoneNum, String cityCode,
			String province, String city, String district, String coordinate, String brief) {
		boolean flag = false;
		if(StringUtil.isEmpty(pid)) { // 新建
			ParkBean park = new ParkBean();
			park.setPid(AppTextUtil.getPrimaryKey());
			park.setPhoneNum(phoneNum);
			park.setName(name);
			park.setAdminId(adminId);
			park.setBrief(brief);
			park.setCity(city);
			park.setCityCode(cityCode);
			park.setContact(contact);
			park.setCoordinate(coordinate);
			park.setDistrict(district);
			park.setPhoneNum(phoneNum);
			park.setProvince(province);
			park.setStatus(0);
			flag = parkImpl.insert(park) > 0;
			if(!flag) {
				throw new ServiceException("add_park_error");
			}
		} else {
			ParkBean park = parkImpl.selectOneById(pid);
			if(park == null) {// 编辑
				throw new ServiceException("object_is_not_exist", "园区");
			}
			
			park.setPhoneNum(phoneNum);
			park.setPhoneNum(phoneNum);
			park.setName(name);
			park.setAdminId(adminId);
			park.setBrief(brief);
			park.setCity(city);
			park.setCityCode(cityCode);
			park.setContact(contact);
			park.setCoordinate(coordinate);
			park.setDistrict(district);
			park.setPhoneNum(phoneNum);
			park.setProvince(province);
			flag = parkImpl.update(park) > 0;
			if(!flag) {
				throw new ServiceException("update_park_error");
			}
			
		}
		return flag;
	}

	@Override
	public boolean del(String adminId, String pid) {boolean flag = false;
		ParkBean park = parkImpl.selectOneById(pid);
		if(park == null) {// 编辑
			throw new ServiceException("object_is_not_exist", "园区");
		}

		park.setStatus(1);// 禁用
		flag = parkImpl.update(park) > 0;
		if(!flag) {
			throw new ServiceException("update_park_error");
		}
		return flag;
	}
	
	

}