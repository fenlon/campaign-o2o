package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.dao.StoreCampaignDao;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreCampaignManagerImpl extends GenericManagerImpl<StoreCampaign, Long> implements StoreCampaignManager {
	
	StoreCampaignDao storeCampaignDao;
	
	@Autowired
	public void setStoreCampaignDao(StoreCampaignDao storeCampaignDao) {
		this.storeCampaignDao = storeCampaignDao;
		this.dao = storeCampaignDao;
	}
	
}
