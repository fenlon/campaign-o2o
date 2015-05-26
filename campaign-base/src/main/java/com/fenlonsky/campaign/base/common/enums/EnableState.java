package com.fenlonsky.campaign.base.common.enums;

public enum EnableState {
	
	DISENABLE("禁用"), ENABLED("启用");
	
	private String value;
	
	private EnableState(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
