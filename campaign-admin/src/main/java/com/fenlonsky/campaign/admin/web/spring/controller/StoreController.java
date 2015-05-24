package com.fenlonsky.campaign.admin.web.spring.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.admin.web.spring.context.CampaignContext;
import com.fenlonsky.campaign.base.dao.utils.PKgen;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Controller
@RequestMapping(value = "/store/")
public class StoreController extends GenericController<Store, Long, StoreManager> {
	
	StoreManager storeManager;
	
	@Autowired
	public void setStoreManager(StoreManager storeManager) {
		this.storeManager = storeManager;
		this.manager = storeManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/store/index";
	}
	
	@Override
	@RequestMapping(value = "/data", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Map<String, Object>> create(@RequestBody @Valid Store model, BindingResult result) {
		AccountUser user = CampaignContext.getSessionAccountUser();
		if (user == null) {
			return asError("您还未登录，请先登录!", null);
		}
		model.setUserId(user.getId());
		String authCode = FenlonDigestUtils.crc32(PKgen.getInstance().nextPK() + "");
		model.setAuthCode(authCode);
		return super.create(model, result);
	}
	
	@RequestMapping(value = "/mobile/detail", method = RequestMethod.GET)
	public String storeDetailUI() {
		return "/customer/store/mobile/detail";
	}
	
	public Map storeInfo(Long id) {
		if (id == null) {
			return null;
		}
		Store store = get(id).getResult();
		return null;
	}
}
