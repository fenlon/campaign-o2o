package com.fenlonsky.campaign.admin.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;

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
	
}
