package com.fenlonsky.campaign.base.common.enums;

public enum CampaignStatus {
	
	NONE_FINISH("未完善"), NOT_STARTED("未开始"), IN_ACTIVE("活动中"), PAUSE("暂停"), CANCLED(
			"已取消"), OVER("已结束");
	
	private String value;
	
	private CampaignStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return this.value;
	}
	
}
