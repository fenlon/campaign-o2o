package com.fenlonsky.campaign.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.dao.StoreCampaignDao;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.base.common.enums.CampaignStatus;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreCampaignManagerImpl extends GenericManagerImpl<StoreCampaign, Long> implements StoreCampaignManager {
	
	StoreCampaignDao storeCampaignDao;
	
	@Autowired
	public void setStoreCampaignDao(StoreCampaignDao storeCampaignDao) {
		this.storeCampaignDao = storeCampaignDao;
		this.dao = storeCampaignDao;
	}
	
	@Override
	public Page<StoreCampaign> findAllByUserId(Long userId, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("page", pageable);
		return this.storeCampaignDao.findAllByConditionByPage("findAllByUserIdByPage", params);
	}
	
	@Override
	public Boolean cancle(Long id, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("userId", userId);
		params.put("status", CampaignStatus.CANCLED);
		return this.dao.executeByCon("cancle", params) > 0 ? true : false;
	}
	
	@Override
	public List<StoreCampaign> findAllActiveByUserId(Long userId, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("status", CampaignStatus.IN_ACTIVE);
		return this.dao.findAllByCondition("findAllActiveByUserId", params);
	}
	
	@Override
	public void updateDeliverNum(long campaignId, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("campaignId", campaignId);
		params.put("userId", userId);
		this.storeCampaignDao.executeByCon("updateDeliverNum", params);
	}
	
	@Override
	public void updateRedeemNum(Long campaignId, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("campaignId", campaignId);
		params.put("userId", userId);
		this.storeCampaignDao.executeByCon("updateRedeemNum", params);
	}
}
