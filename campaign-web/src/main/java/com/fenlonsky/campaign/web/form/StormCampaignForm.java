package com.fenlonsky.campaign.web.form;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class StormCampaignForm {
	
	/** 用户名 **/
	@Length(min = 4, max = 20, message = "活动名称必须为4-20位")
	@NotEmpty(message = "活动名称不能为空")
	@NotBlank(message = "活动名称不能为空")
	private String name;
	
	/** 开始日期 **/
	Date startDate;
	
	/** 结束日期 **/
	Date endDate;
	
	/** 预计总量 **/
	@Min(value = 10, message = "总量最少为10")
	Integer quantity;
	
	/** 红包价值 **/
	@Min(value = 5, message = "红包价值最少为5")
	Integer price;
	
	/** 是否重读领取 **/
	Boolean repeatGet;
	
	/** 活动链接 **/
	@Pattern(regexp = "^http://.*", message = "链接不合法")
	String link;
	
	/** 活动链接 **/
	@Max(value = 250, message = "活动描述最长为250")
	String describtion;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Boolean getRepeatGet() {
		return repeatGet;
	}
	
	public void setRepeatGet(Boolean repeatGet) {
		this.repeatGet = repeatGet;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDescribtion() {
		return describtion;
	}
	
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	
}
