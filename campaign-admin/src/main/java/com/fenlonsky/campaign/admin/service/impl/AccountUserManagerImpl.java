package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.dao.AccountUserDao;
import com.fenlonsky.campaign.admin.service.AccountUserManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class AccountUserManagerImpl extends GenericManagerImpl<AccountUser, Long> implements AccountUserManager {
	
	AccountUserDao accountUserDao;
	
	@Autowired
	public void setAccountDao(AccountUserDao accountUserDao) {
		this.accountUserDao = accountUserDao;
		this.dao = accountUserDao;
	}
	
}
