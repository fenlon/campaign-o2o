package com.fenlonsky.campaign.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.admin.bean.Location;
import com.fenlonsky.campaign.admin.dao.LocationDao;
import com.fenlonsky.campaign.admin.service.LocationManager;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;

@Service
public class LocationManagerImpl extends GenericManagerImpl<Location, Long> implements LocationManager {
	
	LocationDao locationDao;
	
	@Autowired
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
		this.dao = locationDao;
	}
	
}
