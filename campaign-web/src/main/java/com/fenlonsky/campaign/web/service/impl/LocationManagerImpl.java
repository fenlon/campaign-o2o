package com.fenlonsky.campaign.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;
import com.fenlonsky.campaign.web.bean.Location;
import com.fenlonsky.campaign.web.dao.LocationDao;
import com.fenlonsky.campaign.web.service.LocationManager;

@Service
public class LocationManagerImpl extends GenericManagerImpl<Location, Long> implements LocationManager {
	
	LocationDao locationDao;
	
	@Autowired
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
		this.dao = locationDao;
	}
	
}
