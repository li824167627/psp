package com.psp.admin.persist.dao;

import java.util.List;

import com.psp.admin.model.AccountBean;
import com.psp.admin.model.ProviderBean;

public interface ProviderDao {

	int insert(ProviderBean provider);

	AccountBean selectAccountByPhone(String phoneNum);

	int insertAccount(AccountBean account);

	int selectProviderCount(int cid);

	List<ProviderBean> selectProviders(int page, int pageSize, int cid);

	ProviderBean selectOneById(String pid);

	int selectAccountCount(String pid);

	List<AccountBean> selectAccounts(int page, int pageSize, String pid);

	AccountBean selectAccountById(String aid);

	int updateAccount(AccountBean account);


}
