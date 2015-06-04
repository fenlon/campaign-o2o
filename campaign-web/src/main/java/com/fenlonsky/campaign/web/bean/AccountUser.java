package com.fenlonsky.campaign.web.bean;

public class AccountUser extends UserInfo {
	
	private static final long serialVersionUID = -5718447268942374720L;
	
	private Double mbay;
	
	private Double lockmbay;
	
	private Long account_id;
	
	public Double getMbay() {
		return mbay;
	}
	
	public void setMbay(Double mbay) {
		this.mbay = mbay;
	}
	
	public Double getLockmbay() {
		return lockmbay;
	}
	
	public void setLockmbay(Double lockmbay) {
		this.lockmbay = lockmbay;
	}
	
	public Long getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
}
