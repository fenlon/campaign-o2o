package com.fenlonsky.campaign.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.bean.StoreOperator;
import com.fenlonsky.campaign.admin.dao.StoreOperatorDao;
import com.fenlonsky.campaign.admin.form.OperatorForm;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.admin.service.StoreOperatorManager;
import com.fenlonsky.campaign.base.common.enums.EnableState;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreOperatorManagerImpl extends GenericManagerImpl<StoreOperator, Long> implements StoreOperatorManager {
	
	StoreOperatorDao storeOperatorDao;
	
	@Autowired
	StoreManager storeManager;
	
	@Autowired
	public void setStoreOperatorDao(StoreOperatorDao storeOperatorDao) {
		this.storeOperatorDao = storeOperatorDao;
		this.dao = storeOperatorDao;
	}
	
	@Override
	public StoreOperator findByAuthCodeAndMobile(String authCode, String mobile) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authCode", authCode);
		params.put("mobile", mobile);
		return this.storeOperatorDao.findByCondition("findByAuthCodeAndMobile", params);
	}
	
	@Override
	@Transactional
	public StoreOperator init(OperatorForm operatorForm) {
		StoreOperator operator = new StoreOperator();
		operator.setMobile(operatorForm.getMobile());
		operator.setPassword(operatorForm.getPassword());
		operator.setAuthCode(operatorForm.getAuthCode());
		operator.setStatus(EnableState.ENABLED);
		// 由此可见是授权码必须为门店的候选码
		Store store = this.storeManager.findByAuthCode(operatorForm.getAuthCode());
		operator.setStoreId(store.getId());
		// 初始化操作员
		operator = this.storeOperatorDao.saveSelective(operator);
		// 修改门店状态为激活状态
		if (!store.getActive()) {
			this.storeManager.updateIsActive(store.getId(), true);
		}
		return operator;
	}
	
}
