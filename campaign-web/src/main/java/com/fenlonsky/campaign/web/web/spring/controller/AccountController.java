package com.fenlonsky.campaign.web.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.web.bean.Account;
import com.fenlonsky.campaign.web.service.AccountManager;

@Controller
@RequestMapping(value = "/account/")
public class AccountController extends GenericController<Account, Long, AccountManager> {
	
	AccountManager accountService;
	
	@Autowired
	public void setAccountService(AccountManager accountService) {
		this.accountService = accountService;
		this.manager = accountService;
	}
	
}
