package com.fenlonsky.campaign.admin.web.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.form.AccountUserFormSimple;
import com.fenlonsky.campaign.admin.service.AccountUserManager;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.web.spring.controller.BaseController;

@Controller
@RequestMapping(value = "register", method = RequestMethod.GET)
public class RegisterController extends BaseController {
	
	AccountUserManager accountUserManager;
	
	@Autowired
	public void setAccountService(AccountUserManager accountUserManager) {
		this.accountUserManager = accountUserManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/main/register";
	}
	
	/**
	 * 用户注册
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Map<String, Object>> register(@Valid @RequestBody AccountUserFormSimple userFormSimple, BindingResult result, HttpSession session) {
		
		Map<String, Object> checkRes = checkIfHasError(result);
		String authCode = (String) session.getAttribute("authCode");
		if (!authCode.equals(userFormSimple.getAuthCode())) {
			checkRes.put("authcode", "验证码不正确");
		}
		if (!userFormSimple.getPassword().equals(userFormSimple.getPasswordConfirm())) {
			checkRes.put("passwordConfirm", "确认密码与密码不一致");
		}
		if (checkRes.size() > 0) {
			return asError("数据验证失败", checkRes);
		}
		
		/** 用户名是否存在 **/
		Boolean exist = this.accountUserManager.checkUserIsExistByName(userFormSimple.getName());
		if (exist) {
			checkRes.put("isexist", "用户名已经存在");
			return asError("用户名已存在", checkRes);
		}
		
		/** 数据填充 **/
		AccountUser user = new AccountUser();
		user.setName(userFormSimple.getName());
		user.setPassword(userFormSimple.getPassword());
		
		DateTime date = DateTime.now();
		user.setDateCreated(date);
		user.setDateModified(date);
		// 系统暂时默认注册就添加100mbay
		user.setMbay(100d);
		user = this.accountUserManager.saveSelective(user);
		checkRes.put("model", user);
		return asSuccess(checkRes);
	}
	
}
