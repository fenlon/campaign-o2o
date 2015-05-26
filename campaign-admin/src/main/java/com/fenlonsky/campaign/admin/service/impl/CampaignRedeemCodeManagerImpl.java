package com.fenlonsky.campaign.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenlonsky.campaign.admin.bean.AccountUser;
import com.fenlonsky.campaign.admin.bean.CampaignRedeemCode;
import com.fenlonsky.campaign.admin.bean.StoreCampaign;
import com.fenlonsky.campaign.admin.dao.CampaignRedeemCodeDao;
import com.fenlonsky.campaign.admin.enums.RedeemCodeStu;
import com.fenlonsky.campaign.admin.service.AccountUserManager;
import com.fenlonsky.campaign.admin.service.CampaignRedeemCodeManager;
import com.fenlonsky.campaign.admin.service.StoreCampaignManager;
import com.fenlonsky.campaign.base.common.enums.CampaignStatus;
import com.fenlonsky.campaign.base.dao.utils.PKgen;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.protocols.APIResultCode;
import com.fenlonsky.campaign.base.service.impl.GenericManagerImpl;
import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

@Service
public class CampaignRedeemCodeManagerImpl extends GenericManagerImpl<CampaignRedeemCode, Long> implements CampaignRedeemCodeManager {
	
	Logger logger = LoggerFactory.getLogger(CampaignRedeemCodeManagerImpl.class);
	
	@Autowired
	AccountUserManager accountUserManager;
	
	@Autowired
	StoreCampaignManager storeCampaignManager;
	
	CampaignRedeemCodeDao campaignRedeemCodeDao;
	
	@Autowired
	public void setCampaignRedeemCodeDao(CampaignRedeemCodeDao campaignRedeemCodeDao) {
		this.campaignRedeemCodeDao = campaignRedeemCodeDao;
		this.dao = campaignRedeemCodeDao;
	}
	
	@Override
	public APIResult<String> judgeValidate(StoreCampaign campaign, String mobile) {
		// ExecuteResult result = new ExecuteResult(true, );
		APIResult<String> result = new APIResult<String>(true, APIResultCode.SUCCESS, "您可以参加活动!", null);
		// 1: 判断活动是否存在
		if (campaign == null) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("未找到该活动信息");
			return result;
		}
		
		// 2: 活动是否支持
		if (!campaign.getStatus().equals(CampaignStatus.IN_ACTIVE)) {
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("活动不支持：状态为:" + campaign.getStatus().getValue());
			return result;
		}
		
		// 3: 判断发放总量
		if (campaign.getDeliverNum() == campaign.getQuantity()) {
			// 发放到达总量,是否修改活动状态呢？
			result.setCode(APIResultCode.ERROR);
			result.setSuccess(false);
			result.setMessage("红包已经发放完啦！");
			return result;
		}
		
		// 4: 根据是否是否允许重复领取，来判断是否生成兑换码
		if (!campaign.isRepeatGet()) {
			// 如果不允许重复领取，则判断
			boolean exist = this.findRedeemCodeByMobileAndId(campaign.getId(), mobile) == null ? true : false;
			if (exist) {
				result.setCode(APIResultCode.ERROR);
				result.setSuccess(false);
				result.setMessage("您已近领取过该活动!");
				return result;
			}
		}
		// 说明没有任何问题，可以发兑换码
		return result;
	}
	
	@Override
	public CampaignRedeemCode findRedeemCodeByMobileAndId(Long id, String mobile) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("mobile", mobile);
		return this.campaignRedeemCodeDao.findByCondition("findRedeemCodeByMobileAndId", params);
	}
	
	@Override
	@Transactional
	public CampaignRedeemCode createRedeemCode(String mobile, Long storeId, StoreCampaign campaign) {
		try {
			DateTime now = DateTime.now();
			long campaignId = campaign.getId();
			
			// 美贝平台分发
			
			// 根据活动找到商家编号
			Long userId = campaign.getUserId();
			// 1:判断商户余额是否足够
			// 查用户当前余额
			AccountUser user = this.accountUserManager.findById(userId);
			double balance = user.getMbay();
			if (balance < campaign.getDeductSend()) {
				// 如果商户余额不足，不创建兑换码,活动也不停止
				this.logger.info("createRedeemCode", "商户余额不足,请及时充值！");
				return null;
			}
			
			// 2：生成兑换码
			CampaignRedeemCode redeemCode = new CampaignRedeemCode();
			redeemCode.setMobile(mobile);
			redeemCode.setCampaignId(campaignId);
			redeemCode.setJoinStoreId(storeId);
			redeemCode.setRedeemStoreId(0);
			// 生成兑换码
			String code = generateCode(PKgen.getInstance().nextPK() + campaignId + "");
			redeemCode.setRedeemCode(code);
			redeemCode.setStatus(RedeemCodeStu.UN_REDEEM);
			redeemCode.setDateCreated(now);
			redeemCode.setCheckCode(generateCode(code));
			redeemCode = this.campaignRedeemCodeDao.saveSelective(redeemCode);
			if (redeemCode == null) {
				return null;
			}
			// 3:生成订单
			// /** 得到活动发放一个兑换码应该扣除的美贝数 **/
			// double price = activityService.findDeliverPriceById(campaignId);
			// StoreCampaignOrder order = new StoreCampaignOrder();
			// order.setUserNumber(userNumber);
			// String number = PKgen.getInstance().nextPK() + "";
			// order.setNumber(number);
			// order.setPrice(price);
			// order.setRedeemCode(redeemCode.getRedeemCode());
			// order.setStatus(OrderStatus.FINISHED);
			// order.setType(StoreCampaginTradeType.DELIVER);
			// order.setDateCreated(now);
			// this.campaignOrderService.create(order);
			
			// 4:扣除活动单位发放美贝数额(发放一个兑换码，扣除相应的美贝)
			user.setMbay(user.getMbay() - campaign.getDeductSend());
			this.accountUserManager.update(user);
			// 5: 解锁扣除的美贝
			// this.assetsService.reduceLockedAmount(userNumber, price);
			
			// 6:修改活动发放数量
			
			this.storeCampaignManager.updateDeliverNum(campaignId, userId);
			
			// 7:修改门店活动记录中门店发放的数量
			// this.storeCampaignRecordDao.updateDeliverNum(storeId,
			// campaignId);
			return redeemCode;
		} catch (Exception e) {
			logger.error("创建兑换码失败：", e.fillInStackTrace());
			return null;
		}
	}
	
	private String generateCode(String source) {
		return FenlonDigestUtils.crc32(source);
	}
}
