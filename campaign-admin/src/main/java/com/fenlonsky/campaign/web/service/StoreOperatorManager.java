package com.fenlonsky.campaign.web.service;

import com.fenlonsky.campaign.base.service.GenericManager;
import com.fenlonsky.campaign.web.bean.StoreOperator;
import com.fenlonsky.campaign.web.form.OperatorForm;

public interface StoreOperatorManager extends GenericManager<StoreOperator, Long> {
	
	StoreOperator findByAuthCodeAndMobile(String authCode, String mobile);
	
	StoreOperator init(OperatorForm operatorForm);
}
