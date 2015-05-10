package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.Account;
import com.fenlonsky.campaign.admin.dao.AccountDao;
import com.fenlonsky.campaign.admin.service.AccountManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class AccountManagerImpl extends GenericManagerImpl<Account, Long> implements AccountManager {
	
	AccountDao accountDao;
	
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
		this.dao = accountDao;
	}
	
}
