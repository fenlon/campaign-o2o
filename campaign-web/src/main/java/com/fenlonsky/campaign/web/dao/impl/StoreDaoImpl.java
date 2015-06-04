package com.fenlonsky.campaign.web.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fenlonsky.campaign.base.dao.impl.GenericDaoImpl;
import com.fenlonsky.campaign.web.bean.Store;
import com.fenlonsky.campaign.web.dao.StoreDao;

@Repository
public class StoreDaoImpl extends GenericDaoImpl<Store> implements StoreDao {
	
	@Override
	public Store findByAuthCode(String authCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authCode", authCode);
		return findByCondition("findByAuthCodess", params);
	}
	
}
