package com.fenlonsky.campaign.base.common.enums;

public enum Gender {
	
	MALE("男"), FEMALE("女");
	
	private String value;
	
	private Gender(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
