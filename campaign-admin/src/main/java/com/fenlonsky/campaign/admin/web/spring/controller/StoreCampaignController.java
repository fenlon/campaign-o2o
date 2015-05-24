package com.fenlonsky.campaign.admin.web.spring.controller;

import java.util.Map;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.commons.utils.FenlonDateFormat;

@Controller
@RequestMapping(value = "/store_campaign/")
public class StoreCampaignController extends GenericController<StoreCampaign, Long, StoreCampaignManager> {
	
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
	public APIResult<Map<String, Object>> createCampaign(@RequestBody @Valid StoreCampaign campaign, BindingResult result) {
		
		Map<String, Object> checkRes = checkIfHasError(result);
		
		if (checkRes.size() > 0) {
			return asError("数据验证失败", checkRes);
		}
		
		DateTime startTime = FenlonDateFormat.stringToTime(campaign.getStartTime().toString("yyyy-MM-dd") + " 00:00:00");
		DateTime endTime = FenlonDateFormat.stringToTime(campaign.getEndTime().toString("yyyy-MM-dd") + " 23:59:59");
		campaign.setStartTime(startTime);
		campaign.setEndTime(endTime);
		campaign = this.storeCampaignManager.saveSelective(campaign);
		checkRes.put("model", campaign);
		return asSuccess(checkRes);
	}
}
