package com.fenlonsky.campaign.admin.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fenlonsky.campaign.base.model.BaseEntityModel;

public class Account extends BaseEntityModel {
	
	private static final long serialVersionUID = -7521313421322482172L;
	
	@NotEmpty(message = "不能为空")
	@NotBlank(message = "不能为空字符")
	@Length(min = 5, message = "最少为5")
	private String number;
	
	@NotEmpty(message = "不能为空")
	@Length(min = 5, max = 20, message = "必须为5-20个字符")
	@Pattern(regexp = "[a-zA-Z]{5,20}", message = "数据非法")
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
