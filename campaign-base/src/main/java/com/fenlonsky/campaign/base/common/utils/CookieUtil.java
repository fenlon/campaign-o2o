package com.fenlonsky.campaign.base.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: CookieUtil
 * @Description: Cookie帮助类
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月26日 下午8:54:32
 * 
 */
public final class CookieUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	
	private CookieUtil() {
	}
	
	/**
	 * @param response
	 * @param name
	 *            cookieName
	 * @param value
	 *            值
	 * @param second
	 *            秒
	 */
	public static void setCookie(HttpServletResponse response, String name,
			String value, int second) {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting cookie '" + name);
		}
		
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(second); // 秒
		response.addCookie(cookie);
	}
	
	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		Cookie returnCookie = null;
		if (cookies == null) {
			return returnCookie;
		}
		for (final Cookie thisCookie : cookies) {
			if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
				returnCookie = thisCookie;
				break;
			}
		}
		return returnCookie;
	}
	
	/**
	 * 删除cookie
	 * 
	 * @param response
	 * @param cookiename
	 */
	public static void deleteCookie(HttpServletResponse response,
			String cookiename) {
		setCookie(response, cookiename, "", 0);
	}
}
