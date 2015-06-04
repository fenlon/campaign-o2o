package com.fenlonsky.campaign.admin.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.bean.Store;
import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.admin.service.StoreManager;
import com.fenlonsky.campaign.admin.web.spring.context.CampaignContext;
import com.fenlonsky.campaign.base.dao.utils.PKgen;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.web.spring.controller.GenericController;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Controller
@RequestMapping(value = "/store/")
public class StoreController extends GenericController<Store, Long, StoreManager> {
	
	StoreManager storeManager;
	
	@Autowired
	StoreCampaignManager storeCampaignManager;
	
	@Autowired
	public void setStoreManager(StoreManager storeManager) {
		this.storeManager = storeManager;
		this.manager = storeManager;
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/admin/store/index";
	}
	
	/**
	 * 根据ID查询用户的门店
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/queryByUIdByPage", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public APIResult<Page<Store>> queryByIdByPage(@RequestParam(value = "pageNumber", required = false) String page, @RequestParam(value = "pageSize", required = false) String limit) {
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
			this.page = this.manager.findAllByUserIdByPage(this.pageable, CampaignContext.getSessionAccountUser().getId());
			return asSuccess(this.page);
		} catch (Exception e) {
			logger.error("获取分页数据失败", e.fillInStackTrace());
			return asError("获取分页数据失败", null);
		}
	}
	
	@Override
	@RequestMapping(value = "/data", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Map<String, Object>> create(@RequestBody @Valid Store model, BindingResult result) {
		AccountUser user = CampaignContext.getSessionAccountUser();
		if (user == null) {
			return asError("您还未登录，请先登录!", null);
		}
		model.setUserId(user.getId());
		String authCode = FenlonDigestUtils.crc32(PKgen.getInstance().nextPK() + "");
		model.setAuthCode(authCode);
		return super.create(model, result);
	}
	
	@RequestMapping(value = "/mobile/storeRedWallUI", method = RequestMethod.GET)
	public String storeDetailUI(String id, Model model) {
		model.addAttribute("storeId", id);
		return "/customer/store/mobile/store_red_wall";
	}
	
	@RequestMapping(value = "/mobile/list_campaign/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> storeInfo(@PathVariable String id) {
		if (id == null) {
			return null;
		}
		Long storeId = Long.valueOf(FenlonDigestUtils.pbeDecrypt(id));
		Map<String, Object> map = new HashMap<String, Object>();
		Store store = this.manager.findById(storeId);
		map.put("store", store);
		
		// 查询门店活动
		List<StoreCampaign> campaigns = storeCampaignManager.findAllActiveByUserId(store.getUserId(), pageable);
		map.put("campaigns", campaigns);
		return map;
	}
	
}
