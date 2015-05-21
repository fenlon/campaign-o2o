package com.fenlonsky.campaign.commons.utils;

import java.util.Random;

/**
 * 
 * @ClassName: MbayRandom
 * @Description: 自定义随机数池
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月18日 下午7:34:37
 * 
 */
public final class FenlonRandom {
	
	private FenlonRandom() {
	}
	
	private static final char DIGITS[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'
			, 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	/**
	 * 根据长度获取随机数(包含数字、字母)
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandom(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * DIGITS.length);
			sb.append(DIGITS[index]);
		}
		return sb.toString();
	}
	
	public static final class Digest {
		
		private Digest() {
		}
		
		private static final Random random = new Random();
		
		/**
		 * 获取六位随机数(只包含数字)
		 * 
		 * @return
		 */
		public static String getSixRandom() {
			StringBuilder sb = new StringBuilder(6);
			for (int i = 0; i < 6; i++) {
				sb.append(random.nextInt(10));
			}
			return sb.toString();
		}
		
		/**
		 * 根据长度获取随机数(只包含数字)
		 * 
		 * @param length
		 * @return
		 */
		public static String getRandom(int length) {
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				sb.append(random.nextInt(10));
			}
			return sb.toString();
		}
	}
}
