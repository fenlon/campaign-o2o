package com.fenlonsky.campaign.commons.utils;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.zip.CRC32;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: FenlonDigestUtils
 * @Description: 加密工具类
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月18日 下午7:35:37
 * 
 */
public final class FenlonDigestUtils {
	
	static Logger logger = LoggerFactory.getLogger(FenlonDigestUtils.class);
	final String SINK = "FenlonX";
	
	private FenlonDigestUtils() {
	}
	
	/**
	 * 通过crc32,根据字符串生成8位随机数
	 * 
	 * @return
	 */
	public static String crc32(String str) {
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		String prefix = Long.toHexString(Long.valueOf(crc32.getValue() + "")).toUpperCase();
		// 判断crc32生成的数是否为8位，如果不足8位，则去补全，补全方法通过MbayRandom工具类实现
		int length = 8 - prefix.length();
		if (length > 0) {
			String rest = FenlonRandom.getRandom(length);
			String result = prefix + rest;
			return result;
		}
		return prefix;
	}
	
	/**
	 * PBE加密
	 * 
	 * @param str
	 * @return
	 */
	public static String pbeEncrypt(String str) {
		return PBEEncryption.encode(str);
	}
	
	/**
	 * PBE解密
	 * 
	 * @param str
	 * @return
	 */
	public static String pbeDecrypt(String str) {
		return PBEEncryption.decode(str);
	}
	
	/**
	 * PBE加密,主要用于URL参数加密
	 * 
	 * @author frank.zong
	 * 
	 */
	private static class PBEEncryption {
		
		// 随机盐
		private static final byte[] SALT = { (byte) 0x21, (byte) 0x21, (byte) 0xF0,
				(byte) 0x55, (byte) 0xC3, (byte) 0x9F, (byte) 0x5A, (byte) 0x75 };
		
		// 加密深度
		private final static int ITERATION_COUNT = 21;
		
		private PBEEncryption() {
		}
		
		public static String encode(String input) {
			if (input == null || input.length() == 0) {
				throw new IllegalArgumentException();
			}
			try {
				
				KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
				AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);
				
				SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
						.generateSecret(keySpec);
				Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
				ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
				
				byte[] enc = ecipher.doFinal(input.getBytes());
				String res = new String(Base64.encodeBase64String(enc));
				// escapes for url
				res = res.replace('+', '-').replace('/', '_').replace("%", "%25")
						.replace("\n", "%0A");
				
				return res;
				
			} catch (Exception e) {
				logger.error("encode：", e.fillInStackTrace());
			}
			
			return "";
			
		}
		
		public static String decode(String token) {
			if (token == null || token.length() == 0) {
				return null;
			}
			try {
				String input = token.replace("%0A", "\n").replace("%25", "%")
						.replace('_', '/').replace('-', '+');
				byte[] dec = Base64.decodeBase64(input.getBytes());
				
				KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
				AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT,
						ITERATION_COUNT);
				SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
						.generateSecret(keySpec);
				Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
				dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
				byte[] decoded = dcipher.doFinal(dec);
				String result = new String(decoded);
				return result;
			} catch (Exception e) {
				FenlonDigestUtils.logger.error("数据加密编码时候出错:", e.fillInStackTrace());
			}
			return null;
		}
	}
}
