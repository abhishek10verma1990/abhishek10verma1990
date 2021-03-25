package com.npst.upi.portal.merchant.utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.npst.upi.portal.merchant.config.RestTemp;
@Service
public class SendNotiService {
	private static final Logger log = LoggerFactory.getLogger(SendNotiService.class);
	String response=null;
	RestTemplate restTemplate=RestTemp.getRestTemplate();
	
	
	public String sendMerchantNotification(String notificationurl, String finalcallbackresp, String contentType) {
		log.trace("Send Merchant Notification {}", finalcallbackresp);
		try {
			
			JSONObject info1 = new JSONObject();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", contentType);
			
			if ("" != finalcallbackresp && null != finalcallbackresp
					&& "" != notificationurl && null != notificationurl) {
					
					info1.put("data", finalcallbackresp);
										
			} 
			HttpEntity<String> httpEntity = new HttpEntity<String>(info1.toString(), httpHeaders);
			response = this.restTemplate.postForObject(notificationurl, httpEntity, String.class);
			log.trace("Merchant Txn Notification Response {} ", response);
			/*if (null != response) {
				responseCode = "200";
			}*/
		} catch (JSONException jsonexception) {
			log.error("JSONException", jsonexception);
		} catch (Exception exception) {
			log.error("Exception", exception);
		} finally {
		}
		log.info("Return Notification Resp Response Code {}", response);
		return response;
	}	

	
}