package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.model.AdminBean;
import com.psp.admin.service.res.PageResult;

public interface AdminService {
	
	/**
	 * 根据token查询销售
	 * @param token
	 * @return
	 */
	AdminBean getAdminByToken(String token);
	
	/**
	 * 根据id查询销售
	 * @param id
	 * @return
	 */
	AdminBean getAdminById(String id);
	
	/**
	 * 更新姓名
	 * @param adminId
	 * @param name
	 * @return
	 */
	boolean updateName(String adminId, String name);
	
	/**
	 * 查询管理员列表
	 * @param adminId
	 * @param page
	 * @param pageSize
	 * @param key
	 * @return
	 */
	PageResult<RAdminBean> getList(String adminId, int page, int pageSize, String key);
	
	/**
	 * 删除管理员账号
	 * @param adminId
	 * @param aid
	 * @return
	 */
	boolean delAdmin(String adminId, String aid);
	
	/**
	 * 重置管理员密码
	 * @param adminId
	 * @param aid
	 * @return
	 */
	boolean resetAdminPwd(String adminId, String aid);
	
	/**
	 * 新建编辑管理员账号
	 * @param adminId
	 * @param aid
	 * @param pid
	 * @param name
	 * @param password
	 * @param confirmPwd
	 * @param phone
	 * @param type
	 * @return
	 */
	boolean editAdmin(String adminId, String aid, String pid, String name, String password, String confirmPwd,
			String phone, int type);
	
	/**
	 * 检查图形验证码
	 * @param imgkey
	 * @param imgcode
	 */
	void checkImgCode(String imgkey, String imgcode);
	
	/**
	 * 发送短息验证码
	 * @param type
	 * @param phone
	 * @return
	 */
	boolean sendVCode(int type, String phone);
	
	/**
	 * 登录接口
	 * @param sessionId
	 * @param phone
	 * @param pwd
	 * @param vcode
	 * @param device
	 * @param ip
	 * @return
	 */
	RAdminBean login(String sessionId, String phone, String pwd, String vcode, String device, String ip);
	
	/**
	 * 更改个人密码
	 * @param adminId
	 * @param pwd
	 * @param newPwd
	 * @param subPwd
	 * @return
	 */
	boolean updatePassWord(String adminId, String pwd, String newPwd, String subPwd);

}
