package com.fenlonsky.campaign.admin.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.service.AccountUserManager;
import com.fenlonsky.campaign.admin.utils.CustomizedPropertyPlaceholderConfigurer;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Controller
@RequestMapping(value = "/user_info/")
public class UserInfoController extends GenericController<AccountUser, Long, AccountUserManager> {
	
	AccountUserManager accountUserManager;
	
	@Autowired
	public void setAccountService(AccountUserManager accountUserManager) {
		this.accountUserManager = accountUserManager;
		this.manager = accountUserManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("qrcodeurl", CustomizedPropertyPlaceholderConfigurer.getContextProperty("fenlon.campaign.qrcode.context"));
		model.addAttribute("weburl", CustomizedPropertyPlaceholderConfigurer.getContextProperty("fenlon.campaign.web.context"));
		return "/customer/user_info/index";
	}
	
	@RequestMapping(value = "base_info", method = RequestMethod.GET)
	public String baseInfoUI() {
		return "/customer/user_info/base_info";
	}
	
	@RequestMapping(value = "store", method = RequestMethod.GET)
	public String store() {
		return "/customer/user_info/store";
	}
	
	@RequestMapping(value = "campaign", method = RequestMethod.GET)
	public String campaign() {
		return "/customer/user_info/campaign";
	}
	
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public String message() {
		return "/customer/user_info/message";
	}
	
	@RequestMapping(value = "info/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AccountUser getUserInfo(@PathVariable("id") String userId) {
		if (userId == null) {
			return null;
		}
		String encodeStr = FenlonDigestUtils.pbeDecrypt(userId);
		Long id = Long.valueOf(encodeStr);
		return this.accountUserManager.findById(id);
	}
	
}
