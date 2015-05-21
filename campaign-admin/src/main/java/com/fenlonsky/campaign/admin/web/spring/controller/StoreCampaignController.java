package com.fenlonsky.campaign.admin.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fenlonsky.campaign.base.web.spring.controller.BaseController;

@Controller
@RequestMapping(value = "/store_campaign/")
public class StoreCampaignController extends BaseController {
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/main/index";
	}
	
}
