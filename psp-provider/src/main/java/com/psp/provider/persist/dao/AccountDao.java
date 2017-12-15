package com.psp.provider.persist.dao;

import java.util.List;

import com.psp.provider.model.AccountBean;

public interface AccountDao {

	AccountBean selectOneById(String sid);

	AccountBean selectOneByPhone(String phone);

	int updateLoginTime(String aid);

	int updatePwd(AccountBean account);

	int updateName(AccountBean account);

	int selectAccountCount(String pid);

	List<AccountBean> selectAccounts(int page, int pageSize, String pid);

	int insertAccount(AccountBean account);

	int updateAccount(AccountBean account);

}
