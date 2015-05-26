package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.StoreOperator;
import com.fenlonsky.campaign.admin.dao.StoreOperatorDao;
import com.fenlonsky.campaign.admin.service.StoreOperatorManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreOperatorManagerImpl extends GenericManagerImpl<StoreOperator, Long> implements StoreOperatorManager {
	
	StoreOperatorDao storeOperatorDao;
	
	@Autowired
	public void setStoreOperatorDao(StoreOperatorDao storeOperatorDao) {
		this.storeOperatorDao = storeOperatorDao;
		this.dao = storeOperatorDao;
	}
	
}
