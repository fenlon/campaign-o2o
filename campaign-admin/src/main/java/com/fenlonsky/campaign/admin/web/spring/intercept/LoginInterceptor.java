package com.fenlonsky.campaign.admin.web.spring.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: LoginInterceptor
 * @Description: 登录拦截器
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月19日 下午4:24:04
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("URL请求：" + request.getRequestURI());
		}
		if (request.getSession().getAttribute("currentUser") == null) {
			// 不知道为什那么无法转发，只能重定向
			response.sendRedirect(request.getContextPath()
					+ "/login/index.html");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
	
}
