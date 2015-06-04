package com.fenlonsky.campaign.admin.web.spring.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;

@Controller
@RequestMapping(value = "/store_campaign_customer/")
public class CustomerStoreCampaignController extends GenericController<StoreCampaign, Long, StoreCampaignManager> {
	
	StoreCampaignManager storeCampaignManager;
	
	@Autowired
	public void setStoreCampaignManager(StoreCampaignManager storeCampaignManager) {
		this.storeCampaignManager = storeCampaignManager;
		this.manager = storeCampaignManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/store_campaign/index";
	}
	
	/**
	 * 创建活动页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create() {
		return "/customer/store_campaign/create";
	}
	
	@RequestMapping(value = "/campaign", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<StoreCampaign> createCampaign(@RequestBody StoreCampaign campaign) {
		System.out.println(campaign.getName());
		System.out.println(campaign.getStartTime());
		return null;
	}
}
