package com.fenlonsky.campaign.admin.web.spring.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fenlonsky.campaign.admin.bean.AccountUser;

/**
 * 
 * @ClassName: CampaignContext
 * @Description:全局容器辅助类
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月24日 下午8:44:57
 * 
 */

public class CampaignContext {
	
	public static HttpServletRequest getServletRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}
	
	public static HttpSession getHttpSession() {
		return getServletRequest().getSession();
	}
	
	public static AccountUser getSessionAccountUser() {
		HttpSession session = getHttpSession();
		AccountUser user = (AccountUser) session.getAttribute("currentUser");
		// user.setId(Long.valueOf(FenlonDigestUtils.pbeDecrypt(user.getEncodeStr())));
		return user;
	}
	
	public static String getSessionAttribute(String attribute) {
		HttpSession session = getHttpSession();
		return (String) session.getAttribute(attribute);
	}
}
