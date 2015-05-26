package com.fenlonsky.campaign.admin.web.spring.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import StormCampaignForm.MobileForm;

import com.fenlonsky.campaign.admin.bean.CampaignRedeemCode;
import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.service.CampaignRedeemCodeManager;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.admin.web.spring.context.CampaignContext;
import com.fenlonsky.campaign.base.common.enums.CampaignStatus;
import com.fenlonsky.campaign.base.dao.utils.PKgen;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.protocols.APIResultCode;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.commons.utils.FenlonDateFormat;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Controller
@RequestMapping(value = "/store_campaign/")
public class StoreCampaignController extends GenericController<StoreCampaign, Long, StoreCampaignManager> {
	
	StoreCampaignManager storeCampaignManager;
	
	@Autowired
	CampaignRedeemCodeManager campaignRedeemCodeManager;
	
	@Autowired
	public void setStoreCampaignManager(StoreCampaignManager storeCampaignManager) {
		this.storeCampaignManager = storeCampaignManager;
		this.manager = storeCampaignManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/store_campaign/index";
	}
	
	/**
	 * 创建活动页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createUI() {
		return "/customer/store_campaign/create";
	}
	
	@RequestMapping(value = "/campaign", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Map<String, Object>> createCampaign(@RequestBody @Valid StoreCampaign campaign, BindingResult result) {
		
		Map<String, Object> checkRes = checkIfHasError(result);
		
		if (checkRes.size() > 0) {
			return asError("数据验证失败", checkRes);
		}
		
		DateTime startTime = FenlonDateFormat.stringToTime(campaign.getStartTime().toString("yyyy-MM-dd") + " 00:00:00");
		DateTime endTime = FenlonDateFormat.stringToTime(campaign.getEndTime().toString("yyyy-MM-dd") + " 23:59:59");
		campaign.setStartTime(startTime);
		campaign.setEndTime(endTime);
		
		// 设置userID
		Long userId = CampaignContext.getSessionAccountUser().getId();
		
		// 活动状态设置
		DateTime now = DateTime.now();
		if (now.isAfter(endTime)) {
			// 如果结束时间在创建之前,设置为结束
			campaign.setStatus(CampaignStatus.OVER);
		} else {
			if (now.isBefore(startTime)) {
				// 如果开始时间在创建时间之后，设置为为开始
				campaign.setStatus(CampaignStatus.NOT_STARTED);
			} else {
				// 如果开始时间在创建时间之前，设置为活动中
				campaign.setStatus(CampaignStatus.IN_ACTIVE);
			}
		}
		
		// 设置活动编号
		campaign.setNumber(PKgen.getInstance().nextPK() + "");
		
		campaign.setUserId(userId);
		campaign = this.storeCampaignManager.saveSelective(campaign);
		checkRes.put("model", campaign);
		return asSuccess(checkRes);
	}
	
	@RequestMapping(value = "cancle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Boolean> cancel(String id) {
		if (id == null) {
			this.logger.info("取消活动失败ID==null");
			return asError("取消活动失败", false);
		}
		Boolean result = this.storeCampaignManager.cancle(Long.valueOf(FenlonDigestUtils.pbeDecrypt(id)), CampaignContext.getSessionAccountUser().getId());
		return asSuccess(result);
	}
	
	@RequestMapping(value = "/queryAllByUser", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Page<StoreCampaign> getByPage(@RequestParam(value = "pageNumber", required = false) String page, @RequestParam(value = "pageSize", required = false) String limit) {
		if (page == null) {
			this.pageNumber = 0;
		} else {
			if (StringUtils.isNotBlank(page)) {
				this.pageNumber = Integer.valueOf(page) - 1;
			} else {
				this.pageNumber = 0;
			}
		}
		
		if (limit != null) {
			if (StringUtils.isNotBlank(limit)) {
				this.pageSize = Integer.valueOf(limit);
			}
		}
		
		this.pageable = new PageRequest(this.pageNumber, this.pageSize,
				new Sort(Direction.ASC, "id"));
		try {
			Long userId = CampaignContext.getSessionAccountUser().getId();
			this.page = this.manager.findAllByUserId(userId, this.pageable);
			return this.page;
		} catch (Exception e) {
			logger.error("获取分页数据失败", e.fillInStackTrace());
			return null;
		}
	}
	
	@RequestMapping(value = "/mobile/detail/{storeId}/{campaignId}", method = RequestMethod.GET)
	public String detail(@PathVariable String storeId, @PathVariable String campaignId, Model model) {
		model.addAttribute("storeId", storeId);
		StoreCampaign canpaign = this.storeCampaignManager.findById(Long.valueOf(FenlonDigestUtils.pbeDecrypt(campaignId)));
		model.addAttribute("campaign", canpaign);
		return "/customer/store_campaign/mobile/detail";
	}
	
	@RequestMapping(value = "/mobile/join", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Object> join(@RequestBody MobileForm data) {
		
		Long storeId =
				Long.valueOf(FenlonDigestUtils.pbeDecrypt(data.getStoreId()));
		StoreCampaign campaign =
				this.storeCampaignManager.findById(Long.valueOf(FenlonDigestUtils.pbeDecrypt(data.getCampaignId())));
		APIResult<Object> result =
				this.campaignRedeemCodeManager.judgeValidate(campaign, data.getMobile());
		if (!result.getSuccess()) {
			return result;
		}
		// 生成兑换码
		CampaignRedeemCode redeemCode =
				this.campaignRedeemCodeManager.createRedeemCode(data.getMobile(), storeId,
						campaign);
		String code = redeemCode.getRedeemCode();
		code = FenlonDigestUtils.pbeEncrypt(code);
		result.setCode(APIResultCode.SUCCESS);
		result.setMessage("领取 红包成功");
		
		String[] obj = new String[2];
		obj[0] = data.getStoreId();
		obj[1] = code;
		
		result.setResult(obj);
		return result;
	}
	
	@RequestMapping(value = "/mobile/{id}", method = RequestMethod.GET)
	@ResponseBody
	public APIResult<StoreCampaign> get(@PathVariable String id) {
		return super.get(Long.valueOf(FenlonDigestUtils.pbeDecrypt(id)));
	}
	
}
