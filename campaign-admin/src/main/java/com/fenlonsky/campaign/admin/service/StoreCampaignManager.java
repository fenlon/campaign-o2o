package com.fenlonsky.campaign.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.base.service.GenericManager;

public interface StoreCampaignManager extends GenericManager<StoreCampaign, Long> {
	
	public Page<StoreCampaign> findAllByUserId(Long userId, Pageable pageable);
	
	public Boolean cancle(Long id, Long userId);
	
	public List<StoreCampaign> findAllActiveByUserId(Long userId, Pageable pageable);
	
	public void updateDeliverNum(long campaignId, Long userId);
	
	public void updateRedeemNum(Long id, Long userId);
}
