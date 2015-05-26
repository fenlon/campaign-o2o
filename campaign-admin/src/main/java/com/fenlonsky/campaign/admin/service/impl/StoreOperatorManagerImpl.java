package com.fenlonsky.campaign.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

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
	
	@Override
	public StoreOperator findByAuthCodeAndMobile(String authCode, String mobile) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authCode", authCode);
		params.put("mobile", mobile);
		return this.storeOperatorDao.findByCondition("findByAuthCodeAndMobile", params);
	}
	
}
