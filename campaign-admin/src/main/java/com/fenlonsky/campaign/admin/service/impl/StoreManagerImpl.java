package com.fenlonsky.campaign.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenlonsky.campaign.admin.bean.Location;
import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.dao.LocationDao;
import com.fenlonsky.campaign.admin.dao.StoreDao;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class StoreManagerImpl extends GenericManagerImpl<Store, Long> implements StoreManager {
	
	StoreDao storeDao;
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.dao = this.storeDao;
	}
	
	@Override
	@Transactional
	public Store save(Store entity) {
		Location location = this.locationDao.saveSelective(entity.getLocation());
		if (location == null) {
			// 说明保存失败
			return null;
		}
		entity.setLocation(location);
		Store s = super.save(entity);
		return s;
	}
	
	@Override
	public Store findByAuthCode(String authCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("authCode", authCode);
		return this.storeDao.findByCondition("findByAuthCode", params);
	}
	
	@Override
	public Boolean updateIsActive(Long id, boolean active) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("active", active);
		return this.storeDao.executeByCon("updateIsActive", params) > 1 ? true : false;
	}
	
	@Override
	public Page<Store> findAllByUserIdByPage(Pageable pageable, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("page", pageable);
		return this.storeDao.findAllByConditionByPage("findAllByUserIdByPage", params);
	}
}
