package com.fenlonsky.campaign.web.web.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fenlonsky.campaign.web.bean.AccountUser;
import com.fenlonsky.campaign.web.service.AccountUserManager;

@Controller
@RequestMapping(value = "/login/")
public class LoginController {
	
	@Autowired
	AccountUserManager accountUserManager;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/main/login";
	}
	
	/**
	 * 登录系统
	 * 
	 * @param userName
	 * @param password
	 * @param authCode
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String userName, String password, String authCode, Model model, HttpSession session) {
		String checkCode = (String) session.getAttribute("authCode");
		if (checkCode == null || !checkCode.equals(authCode)) {
			return "";
		}
		AccountUser user = this.accountUserManager.findByNameAndPwd(userName, password);
		if (user == null) {
			return "redirect:/index.htm";
		}
		
		session.setAttribute("currentUser", user);
		return "redirect:/index.htm";
	}
	
	/**
	 * 退出系统
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "loginout", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/index.htm";
	}
}
