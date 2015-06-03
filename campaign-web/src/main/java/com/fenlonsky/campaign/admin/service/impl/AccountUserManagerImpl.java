package com.fenlonsky.campaign.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

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
	
	@Override
	public AccountUser findByName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return this.dao.findByCondition("findByName", map);
	}
	
	@Override
	public Boolean checkUserIsExistByName(String name) {
		return findByName(name) == null ? false : true;
	}
	
	@Override
	public AccountUser findByNameAndPwd(String userName, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", userName);
		params.put("password", password);
		return this.dao.findByCondition("findByNameAndPwd", params);
	}
}
