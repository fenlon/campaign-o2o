package com.fenlonsky.campaign.admin.bean;

import com.fenlonsky.campaign.base.model.BaseEntityModel;

public class Store extends BaseEntityModel {
	
	private static final long serialVersionUID = 8710153581177345533L;
	
	/** 用户ID **/
	private Long userId;
	/** 门店编号 **/
	private String number;
	/** 门店名称 **/
	private String name;
	/** 门店授权码 **/
	private String authCode;
	/** 是否禁用 **/
	private Boolean enable = true;
	/** 是否激活 **/
	private Boolean active = false;
	/** 门店位置 **/
	private Location location;
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthCode() {
		return authCode;
	}
	
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public Boolean getEnable() {
		return enable;
	}
	
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
}
