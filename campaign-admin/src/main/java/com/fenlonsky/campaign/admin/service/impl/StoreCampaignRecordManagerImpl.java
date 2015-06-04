package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.StoreCampaignRecord;
import com.fenlonsky.campaign.admin.dao.StoreCampaignRecordDao;
import com.fenlonsky.campaign.admin.service.StoreCampaignRecordManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreCampaignRecordManagerImpl extends GenericManagerImpl<StoreCampaignRecord, Long> implements StoreCampaignRecordManager {
	
	StoreCampaignRecordDao storeCampaignRecordDao;
	
	@Autowired
	public void setStoreCampaignRecordDao(StoreCampaignRecordDao storeCampaignRecordDao) {
		this.storeCampaignRecordDao = storeCampaignRecordDao;
		this.dao = storeCampaignRecordDao;
	}
	
}
