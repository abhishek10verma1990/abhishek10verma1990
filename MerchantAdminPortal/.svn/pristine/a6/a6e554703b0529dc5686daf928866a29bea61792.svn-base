package com.npst.upi.portal.merchant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npst.upi.portal.merchant.dao.MerchantTxnDao;
import com.npst.upi.portal.merchant.dto.MerchantsTxn;
import com.npst.upi.portal.merchant.service.MercahntCallBackservic;
import com.npst.upi.portal.merchant.service.MerchantTxnService;
import com.npst.upi.portal.merchant.utility.MerchantSMS;

@Service
@Transactional
public class MerchantTxnServiceImpl implements MerchantTxnService {
	
	@Autowired 
	MerchantTxnDao merchanttxndao;
	@Autowired
	MercahntCallBackservic merchantcallback;
	
	private static final Logger	log	= LoggerFactory.getLogger(MerchantTxnServiceImpl.class);
	@Autowired
	MerchantSMS merchantSMS;
	public void processMerchantTxn(MerchantsTxn merchantTxnReq) {
		
		try {
		
			if(merchantTxnReq.isBharatQR() && "CREDIT".equals(merchantTxnReq.getTxnType())) {
			log.info("Sending Notification for Bharat QR Txn {}",merchantTxnReq.getTxnId());
			
			merchantcallback.callbacksNotification(merchantTxnReq);
		}
			log.info("processing for SAVE Merchant Txn {}",merchantTxnReq.getTxnId());

		merchanttxndao.saveMerchantTxn(merchantTxnReq);
		
		if("CREDIT".equals(merchantTxnReq.getTxnType())&&"SUCCESS".equals(merchantTxnReq.getStatus())) {
			
			merchantSMS.processSms(merchantTxnReq);
		}
		
		}catch (Exception e) {
			log.error("Exception In processMerchantTxn {}", e);
			
		}
	
	}
	
	

}
