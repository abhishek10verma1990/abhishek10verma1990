package com.npst.upi.portal.merchant.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.dao.MerchantNotificationDao;
import com.npst.upi.portal.merchant.dto.MerchantUpiResponseEntity;
import com.npst.upi.portal.merchant.dto.MerchantsTxn;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.service.MercahntCallBackservic;
import com.npst.upi.portal.merchant.utility.AESSecurity;
import com.npst.upi.portal.merchant.utility.Constants;
import com.npst.upi.portal.merchant.utility.SHA256Algoritham;
import com.npst.upi.portal.merchant.utility.SendNotiService;
import com.npst.upi.portal.merchant.utility.Utility;
@Service
public class MercahntCallBackservicImpl implements MercahntCallBackservic {
	private static final Logger	log	= LoggerFactory.getLogger(MercahntCallBackservicImpl.class);

	private static String contentType= Constants.CONTENT_TYPE; 
    private static String encriptionkey=Utility.getProperty(Constants.ENCRIPTED_KEY);

	private String finalcallbackresp="";
	Merchants merchant = null;
	@Autowired
	MerchantsRepo merchantrepo;
	@Autowired 
	MerchantNotificationDao merchantnotidao;
	private String notificationurl=Utility.getProperty(Constants.NOTI_URL);
	@Autowired 
	SendNotiService sendnotiservice;
	@Override
	public void callbacksNotification(MerchantsTxn merchantTxnReq) {
		try {
			merchant = merchantrepo.findByMerchantVPA(merchantTxnReq.getPayeeAdd());
		    SHA256Algoritham sha256Alg = new SHA256Algoritham();
		    AESSecurity aessecurity=new AESSecurity();
			JSONObject callbackresp = new JSONObject();
			callbackresp.put(Constants.MERCHANT_ID, merchant.getUniqueMid());
			callbackresp.put(Constants.MERCHANT_VPA, merchantTxnReq.getPayeeAdd());
			callbackresp.put(Constants.CUSTOMER_VPA, merchantTxnReq.getPayerVpa());
			callbackresp.put(Constants.MERCANT_TXN_ID,merchantTxnReq.getTxnId() );
			callbackresp.put(Constants.TXN_TIME_STAMP, merchantTxnReq.getCreatedAt());
			callbackresp.put(Constants.TXN_AMOUNT, merchantTxnReq.getAmount());
			
			callbackresp.put(Constants.GATEWAY_TXN_ID, merchantTxnReq.getTxnId());
			if(merchantTxnReq.getStatus()==Constants.SUCCESS) {
				
				callbackresp.put(Constants.GATEWAY_RESP_CODE, Constants.SUCCESS_RESP);

				
			}
			else {
				callbackresp.put(Constants.GATEWAY_RESP_CODE, merchantTxnReq.getNpciErrorCode());

			}
			
				if(merchantTxnReq.getStatus()==Constants.FAILURE) {
			 callbackresp.put(Constants.GATEWAY_RESP_MSG, Constants.FAIL_MSG);
			}
				else {
					 callbackresp.put(Constants.GATEWAY_RESP_MSG, Constants.SUCCESS_MSG);

				}
			
			callbackresp.put(Constants.RRN, merchantTxnReq.getCustomerRefId());
			
			JSONObject checksum = new JSONObject();
			checksum.put(Constants.MERCHANT_ID, merchant.getUniqueMid());
			checksum.put(Constants.MERCHANT_VPA, merchantTxnReq.getPayeeAdd());
			checksum.put(Constants.CUSTOMER_VPA, merchantTxnReq.getPayerVpa());
			checksum.put(Constants.MERCANT_TXN_ID, merchantTxnReq.getTxnId());
			checksum.put(Constants.TXN_TIME_STAMP, merchantTxnReq.getCreatedAt());
			checksum.put(Constants.TXN_AMOUNT, merchantTxnReq.getAmount());
			checksum.put(Constants.GATEWAY_TXN_ID, merchantTxnReq.getTxnId());
			
			
			if(merchantTxnReq.getStatus()==Constants.SUCCESS) {
			
			checksum.put(Constants.GATEWAY_RESP_CODE, Constants.SUCCESS_RESP);
			
			}
			else
			{
				checksum.put(Constants.GATEWAY_RESP_CODE, merchantTxnReq.getNpciErrorCode());
			}
			
			if(merchantTxnReq.getStatus()==Constants.FAILURE) {
				checksum.put(Constants.GATEWAY_RESP_MSG,Constants.FAIL_MSG);
				}
					else {
						checksum.put(Constants.GATEWAY_RESP_MSG, Constants.SUCCESS_MSG);

					}
			
			checksum.put(Constants.RRN, merchantTxnReq.getCustomerRefId());
			
			
			
			
			callbackresp.put(Constants.CHECKSUM, sha256Alg.sha256(checksum.toString()));
			
			//jsondata.put(Constants.DATA, aessecurity.encrypt(callbackresp.toString(), encriptionkey));
			finalcallbackresp=aessecurity.encrypt(callbackresp.toString(), encriptionkey);
			
			log.info("Sring send As Notification {]",finalcallbackresp);
			String responce=sendCallBack(finalcallbackresp,notificationurl,contentType,merchantTxnReq.getTxnId());
			
			log.trace("Recive Notification Resp {}" ,responce);
			
		} catch (Exception e) {
			log.error("Exception callBack", e);
			
		}
		
	}
	
	private String sendCallBack(String finalcallbackresp, String notificationurl, String contentType, String txnId) {
		String objResponse = null;
		String responseCode = null;

		try {
			
			
			if ((!notificationurl.isEmpty())&&(notificationurl!=null)){
				log.info("URL for Notification {}",notificationurl);
				if((!finalcallbackresp.isEmpty())&&(finalcallbackresp!=null)){
					
					log.info("call URL for Txn ID {} ",txnId);
					objResponse=sendnotiservice.sendMerchantNotification(notificationurl, finalcallbackresp, contentType);
					if (null != objResponse) {
						responseCode = "200";
					}
					log.info("Responce {} from call back URL {}",objResponse,notificationurl);
					log.info("staring in merchant_notification Table for txnid {}",txnId);
					merchantnotidao.storeNofification(objResponse,txnId,finalcallbackresp,responseCode);
					
				}
				
			}
		}
		catch (Exception e) {
    log.error("error in send Txn Notification {}",e );	
    }
		return objResponse;
	}

	
}

