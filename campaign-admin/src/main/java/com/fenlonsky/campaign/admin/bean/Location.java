package com.fenlonsky.campaign.admin.bean;

import com.fenlonsky.campaign.base.model.BaseEntityModel;

/**
 * 
 * @ClassName: Location
 * @Description: 地理位置实体类(后来或许还会扩充完善实体)
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月10日 下午2:21:28
 * 
 */
public class Location extends BaseEntityModel {
	
	private static final long serialVersionUID = -9003843154187388979L;
	/** 经度 **/
	private Float lng;
	/** 纬度 **/
	private Float lat;
	/** 详细地址 **/
	private String address;
	/** 显示内容 **/
	private String content;
	
	public Float getLng() {
		return lng;
	}
	
	public void setLng(Float lng) {
		this.lng = lng;
	}
	
	public Float getLat() {
		return lat;
	}
	
	public void setLat(Float lat) {
		this.lat = lat;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
