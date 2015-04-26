package com.fenlonsky.campaign.admin.bean;

import com.fenlonsky.campaign.base.common.enums.Gender;
import com.fenlonsky.campaign.base.model.BaseEntityModel;

public abstract class UserInfo extends BaseEntityModel {
	
	private static final long serialVersionUID = -8138602088316927632L;
	
	private String name;
	
	private String nickName;
	
	private String password;
	
	private Byte age;
	
	private String number;
	
	private String email;
	
	private String mobile;
	
	private String address;
	
	private Gender gender;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Byte getAge() {
		return age;
	}
	
	public void setAge(Byte age) {
		this.age = age;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
