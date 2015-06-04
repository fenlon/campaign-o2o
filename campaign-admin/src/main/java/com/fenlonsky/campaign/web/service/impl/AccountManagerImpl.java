package com.fenlonsky.campaign.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;
import com.fenlonsky.campaign.web.bean.Account;
import com.fenlonsky.campaign.web.dao.AccountDao;
import com.fenlonsky.campaign.web.service.AccountManager;

@Service
public class AccountManagerImpl extends GenericManagerImpl<Account, Long> implements AccountManager {
	
	AccountDao accountDao;
	
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
		this.dao = accountDao;
	}
	
}
