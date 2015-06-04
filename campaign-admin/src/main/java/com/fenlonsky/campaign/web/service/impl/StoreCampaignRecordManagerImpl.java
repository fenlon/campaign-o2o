package com.fenlonsky.campaign.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;
import com.fenlonsky.campaign.web.bean.StoreCampaignRecord;
import com.fenlonsky.campaign.web.dao.StoreCampaignRecordDao;
import com.fenlonsky.campaign.web.service.StoreCampaignRecordManager;

@Service
public class StoreCampaignRecordManagerImpl extends GenericManagerImpl<StoreCampaignRecord, Long> implements StoreCampaignRecordManager {
	
	StoreCampaignRecordDao storeCampaignRecordDao;
	
	@Autowired
	public void setStoreCampaignRecordDao(StoreCampaignRecordDao storeCampaignRecordDao) {
		this.storeCampaignRecordDao = storeCampaignRecordDao;
		this.dao = storeCampaignRecordDao;
	}
	
}
