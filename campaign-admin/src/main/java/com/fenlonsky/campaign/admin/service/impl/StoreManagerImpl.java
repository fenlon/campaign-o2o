package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.dao.StoreDao;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreManagerImpl extends GenericManagerImpl<Store, Long> implements StoreManager {
	
	StoreDao storeDao;
	
	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.dao = this.storeDao;
	}
	
}
