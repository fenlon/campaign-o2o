package com.fenlonsky.campaign.web.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.web.bean.AccountUser;
import com.fenlonsky.campaign.web.service.AccountUserManager;

@Controller
@RequestMapping(value = "/account_user/")
public class AccountUserController extends GenericController<AccountUser, Long, AccountUserManager> {
	
	AccountUserManager accountUserManager;
	
	@Autowired
	public void setAccountService(AccountUserManager accountUserManager) {
		this.accountUserManager = accountUserManager;
		this.manager = accountUserManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/accountUser/index";
	}
	
}
