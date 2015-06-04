package com.fenlonsky.campaign.web.web.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/authcode/")
public class AuthImgController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthImgController.class);
	
	/**
	 * 去掉验证码
	 * 
	 * @param session
	 */
	public static void removeAuthcode(HttpSession session) {
		session.removeAttribute("authcode");
	}
	
	/**
	 * 产生一个随机的数字组成的验证图片
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAuthImg")
	public void getAuthImg(HttpServletRequest request, HttpServletResponse response) {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setWidth(120);
		cs.setHeight(33);
		RandomFontFactory font = new RandomFontFactory();
		font.setMinSize(28);
		font.setMaxSize(28);
		RandomWordFactory word = new RandomWordFactory();
		word.setMinLength(5);
		word.setMaxLength(5);
		cs.setWordFactory(word);
		cs.setFontFactory(font);
		
		try {
			String patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png",
					response.getOutputStream());
			request.getSession().setAttribute("authCode", patchca);
		} catch (IOException e) {
			LOGGER.error("生成随机验证码异常：", e.fillInStackTrace());
		}
		
	}
	
	/**
	 * 验证验证码是否正确
	 * 此验证绑定了validateform的ajax验证要求。返回Y。
	 * 
	 * @param authcode
	 * @param request
	 * @return
	 */
	@RequestMapping("valAuthCode")
	@ResponseBody
	public Boolean validateAuthCode(HttpServletRequest request) {
		String authcode = request.getParameter("authcode");
		if (authcode.equals(request.getSession().getAttribute("authcode"))) {
			return true;
		}
		return false;
	}
	
}
