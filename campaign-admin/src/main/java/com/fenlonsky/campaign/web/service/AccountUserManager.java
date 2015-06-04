package com.fenlonsky.campaign.web.service;

import com.fenlonsky.campaign.base.service.GenericManager;
import com.fenlonsky.campaign.web.bean.AccountUser;

public interface AccountUserManager extends GenericManager<AccountUser, Long> {
	
	public AccountUser findByName(String name);
	
	public Boolean checkUserIsExistByName(String name);
	
	public AccountUser findByNameAndPwd(String userName, String password);
}
