package com.npst.upi.portal.merchant.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.npst.upi.portal.merchant.dto.MerchantUpiRequestDto;
import com.npst.upi.portal.merchant.dto.MerchantUpiResponseDto;
import com.npst.upi.portal.merchant.dto.MerchantUpiResponseEntity;
import com.npst.upi.portal.merchant.utility.HttpUtility;
import com.npst.upi.portal.merchant.utility.MarchantCryptoUtil;
import com.npst.upi.portal.merchant.utility.Utility;
/**
 * 
 * @author npst
 *
 */
public class MerchantUpiReqRunnable implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(MerchantUpiReqRunnable.class);
	protected static ConcurrentMap<String, MerchantUpiResponseEntity> safeRespEntityMap = new ConcurrentHashMap<>();
	private MerchantUpiRequestDto reqObj;
	private String merchantIDHeaderProperty;
	private String merchantId;
	private String upiSwitchURL;

	public MerchantUpiReqRunnable(MerchantUpiRequestDto reqObj, String merchantId, String merchantIDHeaderProperty,
			String upiSwitchURL) {
		this.reqObj = reqObj;
		this.merchantId = merchantId;
		this.merchantIDHeaderProperty = merchantIDHeaderProperty;
		this.upiSwitchURL = upiSwitchURL;
	}

	@Override
	public void run() {
		try {

			createMarchantData(reqObj, merchantId, merchantIDHeaderProperty, upiSwitchURL);

		} catch (Exception e) {
			log.error("Error : {}", e);
			e.printStackTrace();
		}
	}

	public static MerchantUpiResponseDto createMarchantData(MerchantUpiRequestDto reqObj, String merchantId,
			String merchantIDHeaderProperty, String upiSwitchURL) throws IOException, JSONException {
		String marchantIdValueEncrypted = MarchantCryptoUtil.getBase64StringEncrypted(merchantId, "test");
		Map<String, String> headerParams = new HashMap<>();
		headerParams.put(merchantIDHeaderProperty, marchantIdValueEncrypted);
		String response = HttpUtility.postHttpData(getFinalMerchentReq(reqObj), headerParams, upiSwitchURL);
		log.info("response from api : {}", response);
		response = Utility.getStringFromKey(response, "respMsg");
		log.info("response after get value part : {}", response);
		response = MarchantCryptoUtil.decryptedString(Base64.getDecoder().decode(response), "test");
		log.info("response decrypted : {}", response);
		MerchantUpiResponseDto respObj = new MerchantUpiResponseDto();
		respObj = (MerchantUpiResponseDto) Utility.getObjectFromJson(response, respObj);
		log.info("respObj:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : {}", respObj);
		MerchantUpiResponseEntity respEntity = new MerchantUpiResponseEntity();
		BeanUtils.copyProperties(respObj, respEntity);
		respEntity.setRespDate(new Date());
		respEntity.setMerchantId(merchantId);
		safeRespEntityMap.put(merchantId, respEntity);
		return respObj;
	}

	private static String getFinalMerchentReq(MerchantUpiRequestDto reqObj) throws JsonProcessingException {
		String out = null;
		String str = Utility.getJsonFromObject(reqObj);
		log.info("str : {}", str);
		out = "{\"reqMsg\":\"" + MarchantCryptoUtil.getBase64StringEncrypted(str, "test") + "\"}";
		log.info("final req json : {}", out);
		return out;
	}
}
