package com.fenlonsky.campaign.web.bean;

/**
 * 
 * @ClassName: StoreCampaign
 * @Description: 门店活动基本信息
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月18日 下午1:12:28
 * 
 */
public class StoreCampaign extends Campaign {
	
	private static final long serialVersionUID = 5284644278134610051L;
	
	/** 兑换码有效期 **/
	private int validity;
	
	/** 预计总量 ***/
	private int quantity;
	
	/** 活动发放价值 ***/
	private float price;
	
	/** 发放总量 ***/
	private int deliverNum;
	
	/** 兑换总量 ***/
	private int redeemNum;
	
	/** 单个手机号是否可重复领取 */
	private boolean repeatGet;
	
	/** 发出一个红包扣除相应的美贝 **/
	private double deductSend = 1;
	
	/** 兑换一个红包扣除相应的美贝 **/
	private double deductReedem = 1;
	
	/** 活动链接 **/
	private String link;
	
	/*** logo对应的id */
	private String logo;
	
	/** 美贝直通车平台发放红包最大数 **/
	private int mbayPlatSend;
	
	/** 活动锁定美贝 **/
	private double lockMbay;
	
	/** 活动消耗美贝 **/
	private double costMbay;
	
	public int getValidity() {
		return validity;
	}
	
	public void setValidity(int validity) {
		this.validity = validity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getDeliverNum() {
		return deliverNum;
	}
	
	public void setDeliverNum(int deliverNum) {
		this.deliverNum = deliverNum;
	}
	
	public int getRedeemNum() {
		return redeemNum;
	}
	
	public void setRedeemNum(int redeemNum) {
		this.redeemNum = redeemNum;
	}
	
	public boolean isRepeatGet() {
		return repeatGet;
	}
	
	public void setRepeatGet(boolean repeatGet) {
		this.repeatGet = repeatGet;
	}
	
	public double getDeductSend() {
		return deductSend;
	}
	
	public void setDeductSend(double deductSend) {
		this.deductSend = deductSend;
	}
	
	public double getDeductReedem() {
		return deductReedem;
	}
	
	public void setDeductReedem(double deductReedem) {
		this.deductReedem = deductReedem;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public int getMbayPlatSend() {
		return mbayPlatSend;
	}
	
	public void setMbayPlatSend(int mbayPlatSend) {
		this.mbayPlatSend = mbayPlatSend;
	}
	
	public double getLockMbay() {
		return lockMbay;
	}
	
	public void setLockMbay(double lockMbay) {
		this.lockMbay = lockMbay;
	}
	
	public double getCostMbay() {
		return costMbay;
	}
	
	public void setCostMbay(double costMbay) {
		this.costMbay = costMbay;
	}
}
