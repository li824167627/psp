package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.res.bean.RUserLogsBean;
import com.psp.admin.controller.res.bean.RUserNewsBean;
import com.psp.admin.service.res.PageResult;

public interface UserService {

	/**
	 * 获取所有客户
	 * @param sid
	 * @param pageSize 
	 * @param page 
	 * @param key 
	 * @param stype 
	 * @param filteType 
	 * @param isALlot 
	 * @return
	 */
	PageResult<RUserBean> getUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot);
	
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
	
	/**
	 * 查询客户数量
	 * @param isAllot
	 * @return
	 */
	int getUserNum(int isAllot);
	
	/**
	 * 分配客户给销售人员
	 * @param aid
	 * @param sid
	 * @param uid
	 * @return
	 */
	boolean allot(String aid, String sid, String uid);
	
	/**
	 * 获取客户消息
	 * @param sid
	 * @param page
	 * @param pageSize
	 * @param stype
	 * @param key
	 * @param uid
	 * @return
	 */
	PageResult<RUserNewsBean> getUserNews(String sid, int page, int pageSize, int stype, String key, String uid);

}
