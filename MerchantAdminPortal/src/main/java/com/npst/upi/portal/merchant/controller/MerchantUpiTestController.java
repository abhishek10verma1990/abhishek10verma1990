package com.npst.upi.portal.merchant.controller;
/*package com.npst.upi.portal.merchant.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.npst.upi.portal.merchant.dto.MerchantUpiRequestDto;
import com.npst.upi.portal.merchant.dto.MerchantUpiResponseDto;
import com.npst.upi.portal.merchant.dto.MerchantUpiResponseEntity;
import com.npst.upi.portal.merchant.dto.MerchantsDto;
import com.npst.upi.portal.merchant.service.MerchantUpiReqRespService;
import com.npst.upi.portal.merchant.service.MerchantUpiSchedularCallService;
import com.npst.upi.portal.merchant.utility.MarchantCryptoUtil;
import com.npst.upi.portal.merchant.utility.Utility;

@RestController
//@RequestMapping(value = URLConstants.BASE_URL)
public class MerchantUpiTestController {
	private static final Logger log = LoggerFactory.getLogger(MerchantUpiTestController.class);
	private static long temp = System.currentTimeMillis();
	@Autowired
	MerchantUpiReqRespService merchantUpiReqRespServiceImpl;
	@Autowired
	MerchantUpiSchedularCallService merchantUpiSchedularCallService;
	@Autowired
	private TaskExecutor taskExecutor;

	@RequestMapping(value = "/testcreatemerchant", method = RequestMethod.POST)
	public ResponseEntity<Object> createM(@RequestHeader HttpHeaders headers, @RequestBody Map<String, String> map) {

		Map<String, String> out = new HashMap<String, String>();
		try {
			log.info("inside create m");
			log.info("headers Merchant-ID =" + headers.get("Merchant-ID"));
			log.info("req came>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			log.info("map:" + map);
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>reqMsg encrypted\n" + map.get("reqMsg"));
			log.info("decryptReq>>>>>>>>>>>>>>reqMsg\n"
					+ MarchantCryptoUtil.decryptedString(Base64.getDecoder().decode(map.get("reqMsg")), "test"));

			MerchantUpiResponseDto respobj = new MerchantUpiResponseDto();
			respobj.setIsUserSavedSuccessfully("YES");
			respobj.setStatus("00");
			respobj.setMccCodesId(temp++ + "");
			out.put("respMsg", getMerchentResp(respobj));
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>out create M");
			return new ResponseEntity<Object>(out, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error:" + e);
			e.printStackTrace();
			out.put("error:", e.getMessage());
		}
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>out create M");
		return new ResponseEntity<Object>(out, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/testmerchant", method = RequestMethod.POST)
	public ResponseEntity<Object> testM(@RequestBody Map<String, String> map) {
		int i = 0;
		Map<String, String> out = new HashMap<String, String>();
		while (i++ < 1000) {
			String merchantId = "mid" + temp++;
			try {
				if (i == 1) {
					log.info("merchantId=" + merchantId);
					Thread.sleep(1000 * 20);
				}
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>inside test");
				merchantUpiReqRespServiceImpl.createMerchantData(new MerchantUpiRequestDto(), merchantId);
				merchantUpiReqRespServiceImpl.createMerchantData(new MerchantsDto());

				log.info("out test");
				out.put("merchantId_" + i, merchantId);
			} catch (Exception e) {
				log.error("Error:" + e);
				e.printStackTrace();
				out.put("error:" + i, e.getMessage());
			}
		}
		log.info("out test");
		return new ResponseEntity<Object>(out, HttpStatus.BAD_REQUEST);
	}

	private static String getMerchentResp(MerchantUpiResponseDto respObj) {
		String out = null;
		String encryKey = "test";
		try {
			String str = Utility.getJsonFromObject(respObj);
			log.info(str);
			out = MarchantCryptoUtil.getBase64StringEncrypted(str, encryKey);
		} catch (JsonProcessingException e) {
			log.error("Error:" + e);
			e.printStackTrace();
		}
		log.info("final req json:\n" + out);
		return out;
	}

	@RequestMapping(value = "/getstatusmerchant/{merchantId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getstatusM(@PathVariable(name = "merchantId") String merchantId) {

		try {
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>get status object");
			MerchantUpiResponseEntity out = merchantUpiReqRespServiceImpl.getMerchantResponse(merchantId);
			log.info("out test");
			if (out != null)
				return new ResponseEntity<Object>(out, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error:" + e);
			e.printStackTrace();
		}
		log.info("out test");
		return new ResponseEntity<Object>("Plz try after some time?", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/triggerrespmerchantapi", method = RequestMethod.GET)
	public ResponseEntity<Object> getTriggerRespM() {
		try {
			log.info(">>>>>>>>>>>>>>>>>>>trigger response schedular");
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					merchantUpiSchedularCallService.callMerchantResponseApi();
				}
			});
			return new ResponseEntity<Object>("ok", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error:" + e);
			e.printStackTrace();
		}
		log.info("out test");
		return new ResponseEntity<Object>("Plz try after some time?", HttpStatus.OK);
	}

	@RequestMapping(value = "/triggerreqmerchantapi", method = RequestMethod.GET)
	public ResponseEntity<Object> getTriggerReqM() {
		try {
			log.info(">>>>>>>>>>>>>>>>>>>trigger request schedular");
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					merchantUpiSchedularCallService.callMerchantRequestApi();
				}
			});
			return new ResponseEntity<Object>("ok", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error:" + e);
			e.printStackTrace();
		}
		log.info("out test");
		return new ResponseEntity<Object>("Plz try after some time?", HttpStatus.OK);
	}
}
*/