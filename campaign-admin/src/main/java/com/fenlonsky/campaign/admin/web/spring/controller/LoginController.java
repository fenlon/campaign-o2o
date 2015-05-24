package com.fenlonsky.campaign.admin.web.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.service.AccountUserManager;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Controller
@RequestMapping(value = "/login/")
public class LoginController {
	
	@Autowired
	AccountUserManager accountUserManager;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/main/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String userName, String password, String authCode, Model model, HttpSession session) {
		String checkCode = (String) session.getAttribute("authCode");
		if (checkCode == null || !checkCode.equals(authCode)) {
			return "";
		}
		AccountUser user = this.accountUserManager.findByNameAndPwd(userName, password);
		if (user == null) {
			return "";
		}
		String a = FenlonDigestUtils.pbeEncrypt(user.getId() + "");
		user.setEncodeStr(a);
		// session中user对象不保存其Id(如果ID要是String类型就不存在着encodeStr这个属性了)
		user.setId(null);
		session.setAttribute("currentUser", user);
		return "redirect:/index.htm";
	}
	
	@RequestMapping(value = "loginout", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/index.htm";
	}
}
