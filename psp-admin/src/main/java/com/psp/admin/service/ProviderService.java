package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RAccountBean;
import com.psp.admin.controller.res.bean.RProviderBean;
import com.psp.admin.service.res.PageResult;

public interface ProviderService {
	
	/**
	 * 新建服务商返回账号
	 * @param name
	 * @param address
	 * @param contact
	 * @param phoneNum
	 * @param content
	 * @param confirmPwd 
	 * @param password 
	 * @param cids 
	 * @return
	 */
	RAccountBean addProvider(String name, String address, String contact, String phoneNum, String content, String password, String confirmPwd, String cids);
	
	
	/**
	 * 获取服务商列表
	 * @param adminId 
	 * @param page
	 * @param pageSize
	 * @param cid
	 * @return
	 */
	PageResult<RProviderBean> getList(String adminId, int page, int pageSize, int cid);

	/**
	 * 获取服务商详情
	 * @param adminId
	 * @param pid
	 * @return
	 */
	RProviderBean getDetail(String adminId, String pid);

	/**
	 * 获取服务商账号列表
	 * @param adminId
	 * @param page
	 * @param pageSize
	 * @param pid
	 * @return
	 */
	PageResult<RAccountBean> getAccountList(String adminId, int page, int pageSize, String pid);
	
	/**
	 * 新建服务商账号
	 * @param adminId
	 * @param name
	 * @param phone
	 * @param password
	 * @param password
	 * @return
	 */
	boolean addAccount(String adminId, String name, String phone, String password, String pid);

	/**
	 * 重置账号密码
	 * @param adminId
	 * @param aid
	 * @return
	 */
	boolean resetPwd(String adminId, String aid);

	/**
	 * 删除账号
	 * @param adminId
	 * @param aid
	 * @return
	 */
	boolean delAccount(String adminId, String aid);

	/**
	 * 编辑服务商
	 * @param name
	 * @param address
	 * @param contact
	 * @param phoneNum
	 * @param content
	 * @return
	 */
	boolean editProvider(String pid, String name, String address, String contact, String phoneNum, String content);


	boolean addService(String pid, String cid);


	boolean delService(String pid, String cid);

}
