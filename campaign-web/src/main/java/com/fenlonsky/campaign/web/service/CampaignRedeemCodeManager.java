package com.fenlonsky.campaign.web.service;

import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.service.GenericManager;
import com.fenlonsky.campaign.web.bean.CampaignRedeemCode;
import com.fenlonsky.campaign.web.bean.StoreCampaign;
import com.fenlonsky.campaign.web.bean.StoreOperator;

public interface CampaignRedeemCodeManager extends GenericManager<CampaignRedeemCode, Long> {
	
	APIResult<Object> judgeValidate(StoreCampaign campaign, String mobile);
	
	CampaignRedeemCode findRedeemCodeByMobileAndId(Long id, String mobile);
	
	CampaignRedeemCode createRedeemCode(String mobile, Long valueOf, StoreCampaign campaign);
	
	CampaignRedeemCode findByCode(String code);
	
	APIResult<Object> redeem(CampaignRedeemCode redeemCode, StoreOperator operator);
}
