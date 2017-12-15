package com.psp.provider.service;

import com.psp.provider.controller.res.bean.RAccountBean;
import com.psp.provider.model.AccountBean;
import com.psp.provider.service.impl.res.PageResult;

public interface AccountService {
	
	/**
	 * 根据token查询服务商账号
	 * @param token
	 * @return
	 */
	AccountBean getAccountByToken(String token);
	
	/**
	 * 根据id查询服务商账号
	 * @param id
	 * @return
	 */
	AccountBean getAccountById(String id);
	
	/**
	 * 登录
	 * @param sessionId
	 * @param phone
	 * @param pwd
	 * @param vcode
	 * @param device
	 * @param ip
	 * @return
	 */
	RAccountBean login(String sessionId, String phone, String pwd, String vcode, String device, String ip);
	
	/**
	 * 重置密码
	 * @param account
	 * @param pwd
	 * @param newPwd
	 * @param subPwd
	 * @return
	 */
	boolean resetPwd(AccountBean account, String pwd, String newPwd, String subPwd);
	
	/**
	 * 更新名称
	 * @param account
	 * @param name
	 * @return
	 */
	RAccountBean updateName(AccountBean account, String name);

	PageResult<RAccountBean> getAccountList(AccountBean account, int page, int pageSize);

	boolean resetAccountPwd(AccountBean account, String aid);

	boolean addAccount(AccountBean account, String name, String phone, String password);

	boolean delAccount(AccountBean account, String aid);

}
