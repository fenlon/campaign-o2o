package com.fenlonsky.campaign.web.enums;

public enum RedeemCodeStu {
	
	UNISSUED("未发放"), UN_REDEEM("未兑换"), REDEEMED("已兑换");
	
	private String value;
	
	private RedeemCodeStu(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
