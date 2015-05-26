package com.fenlonsky.campaign.admin.service;

import com.fenlonsky.campaign.admin.bean.CampaignRedeemCode;
import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.service.GenericManager;

public interface CampaignRedeemCodeManager extends GenericManager<CampaignRedeemCode, Long> {
	
	APIResult<String> judgeValidate(StoreCampaign campaign, String mobile);
	
	CampaignRedeemCode findRedeemCodeByMobileAndId(Long id, String mobile);
	
	CampaignRedeemCode createRedeemCode(String mobile, Long valueOf, StoreCampaign campaign);
}
