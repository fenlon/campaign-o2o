package com.fenlonsky.campaign.admin.service;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.base.service.GenericManager;

public interface AccountUserManager extends GenericManager<AccountUser, Long> {
	
	public AccountUser findByName(String name);
	
	public Boolean checkUserIsExistByName(String name);
	
	public AccountUser findByNameAndPwd(String userName, String password);
}
