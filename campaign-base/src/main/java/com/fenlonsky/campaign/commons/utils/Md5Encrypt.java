package com.fenlonsky.campaign.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @ClassName: Md5Encrypt
 * @Description: 自定义加密
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月18日 下午7:30:19
 * 
 */
public class Md5Encrypt {
	
	private static final char DIGITS[] = { '0', '1', '2', '3', '4', '5', 'f', 'e', 'n', 'l', 'o', 'n', 'x', 'i', 'o', 'n', 'g' };
	
	public Md5Encrypt() {
	}
	
	public static String md5(String text) {
		return md5(text, "UTF-8");
	}
	
	public static String md5(String text, String charset) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		try {
			msgDigest.update(text.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		byte bytes[] = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}
	
	public static char[] encodeHex(byte data[]) {
		int l = data.length;
		char out[] = new char[l << 1];
		int i = 0;
		int j = 0;
		for (; i < l; i++) {
			out[j++] = DIGITS[(0xf0 & data[i]) >>> 4];
			out[j++] = DIGITS[0xf & data[i]];
		}
		return out;
	}
}
