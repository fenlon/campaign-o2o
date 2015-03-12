package com.fenlonsky.campaign.qrcode.entity;

import java.io.Serializable;

import com.fenlonsky.campaign.qrcode.util.QRCodeUtil;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode implements Serializable {

	private static final long serialVersionUID = 6425927132341801365L;
	/** 编码内容 **/
	private String content;
	/** 二维码容错等级 **/
	private ErrorCorrectionLevel errorLevel = ErrorCorrectionLevel.L;
	/** 大小 **/
	private Integer size = QRCodeUtil.DEFAULT_HEIGHT;
	/** 前景色 **/
	private Integer foregroundColor = QRCodeUtil.DEFAULT_FORE_GROUND_COLOR;
	/** 背景色 **/
	private Integer backGroundColor = QRCodeUtil.DEFAULT_BACK_GROUND_COLOR;
	/** logo URL **/
	private String logoUrl;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ErrorCorrectionLevel getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(ErrorCorrectionLevel errorLevel) {
		this.errorLevel = errorLevel;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Integer foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public Integer getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(Integer backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
