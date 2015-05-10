package com.fenlonsky.campaign.admin.bean;

import org.joda.time.DateTime;

import com.fenlonsky.campaign.admin.enums.CampaignStatus;
import com.fenlonsky.campaign.base.model.BaseEntityModel;

public class Campaign extends BaseEntityModel {
	
	private static final long serialVersionUID = 7949003351642549215L;
	/** 活动编号 **/
	private String number;
	/** 活动名称 **/
	private String name;
	/** 用户编号 **/
	private String userNumber;
	/** 开始时间 **/
	private DateTime startTime;
	/** 结束时间 **/
	private DateTime endTime;
	/** 活动状态 **/
	private CampaignStatus status;
	/** 描述 **/
	private String describtion;
	
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
	
	public String getUserNumber() {
		return userNumber;
	}
	
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
	public DateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
	
	public DateTime getEndTime() {
		return endTime;
	}
	
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	
	public CampaignStatus getStatus() {
		return status;
	}
	
	public void setStatus(CampaignStatus status) {
		this.status = status;
	}
	
	public String getDescribtion() {
		return describtion;
	}
	
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
}
