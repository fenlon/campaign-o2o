package com.fenlonsky.campaign.admin.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class AccountUserFormSimple {
	
	/** 用户名 **/
	@Length(min = 5, max = 12, message = "用户名必须为5-12位")
	@NotEmpty(message = "用户名不能为空")
	@NotBlank(message = "用户名不能为空")
	@Pattern(regexp = "[a-zA-Z]{1}[a-zA-Z0-9]+", message = "用户名首位必须为字符")
	private String name;
	/** 密码 **/
	@Length(min = 5, max = 12, message = "密码必须为5-12位")
	@NotEmpty(message = "密码不能为空")
	@NotBlank(message = "密码不能为空")
	private String password;
	
	/** 确认密码 */
	private String passwordConfirm;
	
	/** 验证码 **/
	private String authCode;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuthCode() {
		return authCode;
	}
	
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
