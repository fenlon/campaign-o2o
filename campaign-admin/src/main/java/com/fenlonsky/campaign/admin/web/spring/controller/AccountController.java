package com.fenlonsky.campaign.admin.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fenlonsky.campaign.admin.bean.Account;
import com.fenlonsky.campaign.admin.service.AccountManager;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;

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
