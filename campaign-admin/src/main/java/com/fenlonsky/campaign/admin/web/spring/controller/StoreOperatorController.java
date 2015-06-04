package com.fenlonsky.campaign.admin.web.spring.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.admin.bean.CampaignRedeemCode;
import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.bean.StoreOperator;
import com.fenlonsky.campaign.admin.enums.RedeemCodeStu;
import com.fenlonsky.campaign.admin.form.OperatorForm;
import com.fenlonsky.campaign.admin.service.CampaignRedeemCodeManager;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.admin.service.StoreOperatorManager;
import com.fenlonsky.campaign.admin.web.spring.context.CampaignContext;
import com.fenlonsky.campaign.base.common.utils.CookieUtil;
import com.fenlonsky.campaign.base.common.utils.RegExp;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.protocols.APIResultCode;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Controller
@RequestMapping(value = "/store_operator/")
public class StoreOperatorController extends GenericController<StoreOperator, Long, StoreOperatorManager>
{
	
	public static final String OPERATOR_LOGIN_COOKIE = "operator_login_cookie";
	/** cookie保存一个月 **/
	public static final int OPERATOR_LOGIN_COOKIE_TIME = 365 * 24 * 60 * 60;
	
	Logger logger = LoggerFactory.getLogger(StoreOperatorController.class);
	
	@Autowired
	StoreManager storeManager;
	
	StoreOperatorManager storeOperatorManager;
	
	@Autowired
	CampaignRedeemCodeManager campaignRedeemCodeManager;
	
	@Autowired
	StoreCampaignManager storeCampaignManager;
	
	@Autowired
	public void setStoreOperatorManager(StoreOperatorManager storeOperatorManager) {
		this.storeOperatorManager = storeOperatorManager;
		this.manager = storeOperatorManager;
	}
	
	/**
	 * 操作员兑换界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mobile/redeem_ui", method = RequestMethod.GET)
	public String redeemUI(String code, Model model) {
		HttpServletRequest request = CampaignContext.getServletRequest();
		Cookie cookie = CookieUtil.getCookie(request, StoreOperatorController.OPERATOR_LOGIN_COOKIE);
		if (cookie == null) {
			// 登录界面
			return "/customer/store_operator/mobile/loginUI";
		}
		
		String value = cookie.getValue();
		String[] vs = value.split("-");
		if (vs.length != 2) {
			return "/o2o_campaign/operator/loginUI";
		}
		
		String mobile = vs[0];
		String authCode = vs[1];
		// 判断Cookie信息是否正确
		StoreOperator operator = this.storeOperatorManager.findByAuthCodeAndMobile(authCode, mobile);
		if (operator == null) {
			return "/o2o_campaign/operator/loginUI";
		}
		// 下面说明Cookie信息正确，且免登录
		model.addAttribute("mobile", mobile);
		// model.addAttribute("authCode", authCode);
		// 判断兑换门店是否禁用
		Store redeemStore = this.storeManager.findByAuthCode(authCode);
		if (!redeemStore.getEnable()) {
			return "/o2o_campaign/operator/loginUI";
		}
		/*
		 * try {
		 * // 转到兑换页面，并将兑换码带过去
		 * //String url = ChannelConfig.getMobileDomain() +
		 * request.getContextPath() + "/store_ope/redeemUI.mbay";
		 * if (code != null) {
		 * url = url + "?code=" + code;
		 * }
		 * } catch (Exception e) {
		 * logger.error("", e.fillInStackTrace());
		 * }
		 */
		// model.addAttribute("storeId", redeemStore.getId());
		// return "/o2o_campaign/store/redeemUI";
		if (code != null) {
			code = FenlonDigestUtils.pbeDecrypt(code);
			CampaignRedeemCode redeemCode = this.campaignRedeemCodeManager.findByCode(code);
			StoreCampaign campaign = this.storeCampaignManager.findById(redeemCode.getCampaignId());
			model.addAttribute("code", code);
			model.addAttribute("campaign", campaign);
		}
		return "/customer/store_operator/mobile/redeemUI";
	}
	
	/**
	 * 操作员登录
	 * 
	 * @param authCode
	 * @param mobile
	 * @param password
	 */
	@RequestMapping(value = "/mobile/login", method = RequestMethod.POST)
	@ResponseBody
	public APIResult<String> login(@RequestBody OperatorForm operatorForm, HttpServletResponse response) {
		APIResult<String> result = new APIResult<String>(true, APIResultCode.SUCCESS, "登录成功", null);
		
		String authCode = operatorForm.getAuthCode();
		Store redeemStore = this.storeManager.findByAuthCode(authCode);
		
		if (authCode == null) {
			result.setCode(APIResultCode.ERROR);
			result.setMessage("授权码错误，门店不存在!");
			result.setSuccess(false);
			return result;
		}
		// 判断兑换门店是否禁用
		if (!redeemStore.getEnable()) {
			result.setCode(APIResultCode.ERROR);
			result.setMessage("门店已经被禁用,无法登陆进系统!");
			result.setSuccess(false);
			return result;
		}
		StoreOperator ope = this.storeOperatorManager.findByAuthCodeAndMobile(authCode, operatorForm.getMobile());
		if (ope == null) {
			result.setSuccess(false);
			result.setCode(APIResultCode.ERROR);
			result.setMessage("用户不存在!");
			return result;
		}
		if (operatorForm.getPassword().trim().equals("") || operatorForm.getPassword() == null) {
			result.setSuccess(false);
			result.setCode(APIResultCode.ERROR);
			result.setMessage("密码错误!");
			return result;
		}
		if (!ope.getPassword().equals(operatorForm.getPassword())) {
			result.setSuccess(false);
			result.setMessage("密码错误");
			result.setCode(APIResultCode.ERROR);
			return result;
		}
		
		// 清楚之前保存的Cookie
		CookieUtil.deleteCookie(response, OPERATOR_LOGIN_COOKIE);
		// 重新设置Cookie
		CookieUtil.setCookie(response, OPERATOR_LOGIN_COOKIE, ope.getMobile() + "-" + ope.getAuthCode(), OPERATOR_LOGIN_COOKIE_TIME);
		return result;
	}
	
	/**
	 * 兑换码兑换
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/mobile/redeem", method = RequestMethod.POST)
	@ResponseBody
	public APIResult<Object> redeem(String code) {
		System.out.println(code);
		APIResult<Object> result = new APIResult<Object>(true, APIResultCode.SUCCESS, "兑换成功！", null);
		HttpServletRequest request = CampaignContext.getServletRequest();
		Cookie cookie = CookieUtil.getCookie(request, StoreOperatorController.OPERATOR_LOGIN_COOKIE);
		if (cookie == null) {
			result.setSuccess(false);
			result.setMessage("请先登录!");
			result.setCode(APIResultCode.ERROR);
			return result;
		}
		CampaignRedeemCode redeemCode = this.campaignRedeemCodeManager.findByCode(code);
		if (redeemCode == null) {
			result.setSuccess(false);
			result.setMessage("兑换码不存在！");
			result.setCode(APIResultCode.ERROR);
			return result;
		}
		if (redeemCode.getStatus().equals(RedeemCodeStu.REDEEMED)) {
			result.setSuccess(false);
			result.setMessage("兑换码已使用，无效!");
			result.setCode(APIResultCode.ERROR);
			return result;
		}
		String[] cs = cookie.getValue().split("-");
		if (cs.length < 2) {
			result.setSuccess(false);
			result.setMessage("系统繁忙!");
			result.setCode(APIResultCode.ERROR);
			this.logger.error("redeem:=", "获取到的cookie有问题");
			return result;
		}
		String cellphone = cs[0];
		String authCode = cs[1];
		StoreOperator operator = this.storeOperatorManager.findByAuthCodeAndMobile(authCode, cellphone);
		result = this.campaignRedeemCodeManager.redeem(redeemCode, operator);
		return result;
	}
	
	/**
	 * 操作员初始化界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mobile/init_ui", method = RequestMethod.GET)
	public String initUI() {
		return "/customer/store_operator/mobile/initUI";
	}
	
	/**
	 * 操作员初始化
	 * 
	 * @param operatorForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/mobile/init", method = RequestMethod.POST)
	@ResponseBody
	public APIResult<String> init(@RequestBody OperatorForm operatorForm, HttpServletResponse response) {
		
		APIResult<String> result = new APIResult<String>(true, APIResultCode.SUCCESS, "初始化成功！", null);
		if (operatorForm.getMobile() == null) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("手机不能为空!");
			return result;
		}
		
		Pattern p = RegExp.mobile;
		Matcher m = p.matcher(operatorForm.getMobile());
		if (m.matches() == false) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("手机格式不正确!");
			return result;
		}
		
		if (!operatorForm.getPassword().equals(operatorForm.getPasswordConfirm())) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("前后密码不一致!");
			return result;
		}
		
		Store store = this.storeManager.findByAuthCode(operatorForm.getAuthCode());
		if (store == null) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("门店授权码不存在！");
			return result;
		}
		
		StoreOperator operator = this.storeOperatorManager.findByAuthCodeAndMobile(operatorForm.getAuthCode(), operatorForm.getMobile());
		if (operator != null) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("该号码已经绑定到此门店，请直接登录!");
			return result;
		}
		operator = this.storeOperatorManager.init(operatorForm);
		if (operator != null) {
			CookieUtil.setCookie(response, OPERATOR_LOGIN_COOKIE, operatorForm.getMobile() + "-" + operatorForm.getAuthCode(), OPERATOR_LOGIN_COOKIE_TIME);
		}
		return result;
	}
	
}
