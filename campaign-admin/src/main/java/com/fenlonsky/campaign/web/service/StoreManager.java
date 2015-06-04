package com.fenlonsky.campaign.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenlonsky.campaign.base.service.GenericManager;
import com.fenlonsky.campaign.web.bean.Store;

public interface StoreManager extends GenericManager<Store, Long> {
	
	Store findByAuthCode(String authCode);
	
	Boolean updateIsActive(Long id, boolean active);
	
	Page<Store> findAllByUserIdByPage(Pageable pageable, Long userId);
}
