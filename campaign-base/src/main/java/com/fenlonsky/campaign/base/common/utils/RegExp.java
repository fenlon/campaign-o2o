package com.fenlonsky.campaign.base.common.utils;

import java.util.regex.Pattern;

/**
 * @Description: 提供常用正则表达式
 * @author han.han
 * @date 2015-2-10 下午6:57:34
 * 
 */
public final class RegExp {
	
	// /手机 "^0?(13|15|18|14|17)[0-9]{9}$",
	public static final Pattern mobile = Pattern.compile("^0?(13|15|18|14|17)[0-9]{9}$"); // 验证手机号
	// 邮箱
	public static final Pattern email = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"); // 验证手机号
	// 图片
	// (.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$
	public static final Pattern picture = Pattern.compile("(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$"); // 验证手机号
	// url: "",
	public static final Pattern url = Pattern.compile("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$"); // 验证手机号
	// 兑换码
	public static final Pattern redeemCode = Pattern.compile("^[0-9a-zA-Z]{8}$"); // 验证手机号
	
}
