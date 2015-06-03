package com.fenlonsky.campaign.admin.service;

import com.fenlonsky.campaign.admin.bean.StoreOperator;
import com.fenlonsky.campaign.admin.form.OperatorForm;
import com.fenlonsky.campaign.base.service.GenericManager;

public interface StoreOperatorManager extends GenericManager<StoreOperator, Long> {
	
	StoreOperator findByAuthCodeAndMobile(String authCode, String mobile);
	
	StoreOperator init(OperatorForm operatorForm);
}
