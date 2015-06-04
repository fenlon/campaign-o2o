package com.fenlonsky.campaign.admin.bean;

import com.fenlonsky.campaign.base.common.enums.EnableState;
import com.fenlonsky.campaign.base.model.BaseEntityModel;

/**
 * 
 * @ClassName: StoreOperator
 * @Description: 门店操作员
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月26日 下午12:24:48
 * 
 */
public class StoreOperator extends BaseEntityModel {
	
	private static final long serialVersionUID = -8894721591484026125L;
	/** 门店ID **/
	private long storeId;
	/** 操作员授权码 **/
	private String authCode;
	/** 操作员手机号 **/
	private String mobile;
	/** 操作员状态 (禁用，启用) **/
	private EnableState status;
	/** 操作员密码 **/
	private String password;
	
	public String getAuthCode() {
		return authCode;
	}
	
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public EnableState getStatus() {
		return status;
	}
	
	public void setStatus(EnableState status) {
		this.status = status;
	}
	
	public long getStoreId() {
		return storeId;
	}
	
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
}
