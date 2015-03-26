package com.fenlonsky.campaign.admin.bean;

import com.fenlonsky.campaign.base.model.BaseEntityModel;

public class Account extends BaseEntityModel {
	
	private static final long serialVersionUID = -7521313421322482172L;
	
	private String number;
	
	private String name;
	
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
	
}
