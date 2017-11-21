package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.res.bean.RUserLogsBean;
import com.psp.admin.service.res.PageResult;

public interface UserService {

	/**
	 * 获取销售下所有客户
	 * @param sid
	 * @param pageSize 
	 * @param page 
	 * @param key 
	 * @param stype 
	 * @param filteType 
	 * @param status 
	 * @return
	 */
	PageResult<RUserBean> getUsers2Seller(String sid, int page, int pageSize, int filteType, int stype, String key, int status);
	
	/**
	 * 新建客户
	 * @param sid
	 * @param name
	 * @param phoneNum
	 * @param companyName
	 * @param position
	 * @param label
	 * @param isUpdate
	 * @param isClaim
	 * @return
	 */
	RUserBean addUser(String sid, String name, String phoneNum, String companyName, String position, String label,
			int isUpdate, int isClaim);
	
	/**
	 * 编辑客户
	 * @param sid
	 * @param name
	 * @param phoneNum
	 * @param companyName
	 * @param position
	 * @param label
	 * @param uid
	 * @return
	 */
	RUserBean eidtUser(String sid, String name, String phoneNum, String companyName, String position, String label,
			String uid);
	/**
	 * 查询客户数量
	 * @param sid
	 * @param isAllot 
	 * @return
	 */
	int getUserNum2Seller(String sid, int isAllot);
	
	/**
	 * 设置客户评级
	 * @param sid
	 * @param level
	 * @param uid
	 * @return
	 */
	boolean eidtUserLevel(String sid, int level, String uid);
	
	/**
	 * 设置客户标签
	 * @param sid
	 * @param label
	 * @param uid
	 * @return
	 */
	boolean eidtUserLabel(String sid, String label, String uid);
	
	/**
	 * 归档客户
	 * @param sid
	 * @param uid
	 * @return
	 */
	boolean archive(String sid, String uid);
	
	/**
	 * 获取客户详情
	 * @param sid
	 * @param uid
	 * @return
	 */
	RUserBean getDetail(String sid, String uid);
	
	/**
	 * 获取客户操作日志列表
	 * @param sid
	 * @param uid
	 * @param key
	 * @return
	 */
	PageResult<RUserLogsBean> getUserLogs(String sid, String uid, String key);

}
