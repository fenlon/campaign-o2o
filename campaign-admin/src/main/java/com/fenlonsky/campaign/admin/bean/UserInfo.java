package com.fenlonsky.campaign.admin.bean;

import com.fenlonsky.campaign.base.common.enums.Gender;
import com.fenlonsky.campaign.base.model.BaseEntityModel;

/**
 * 
 * @ClassName: UserInfo
 * @Description: 用户基本信息抽象类
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年4月26日 上午10:49:57
 * 
 */
public abstract class UserInfo extends BaseEntityModel {
	
	private static final long serialVersionUID = -8138602088316927632L;
	/** 姓名 **/
	private String name;
	/** 呢称 **/
	private String nickName;
	/** 密码 **/
	private String password;
	/** 年龄 **/
	private Byte age;
	/** 编号 **/
	private String number;
	/** 邮箱 **/
	private String email;
	/** 手机 **/
	private String mobile;
	/** 地址 **/
	private String address;
	/** 性别 **/
	private Gender gender;
	/** 头像 **/
	private String photo;
	
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
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
