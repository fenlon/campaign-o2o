package com.fenlonsky.campaign.web.dao;

import com.fenlonsky.campaign.base.dao.GenericDao;
import com.fenlonsky.campaign.web.bean.Store;

public interface StoreDao extends GenericDao<Store, Long> {
	
	Store findByAuthCode(String authCode);
}
