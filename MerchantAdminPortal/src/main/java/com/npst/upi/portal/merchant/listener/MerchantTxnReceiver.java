package com.npst.upi.portal.merchant.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.npst.upi.portal.merchant.dto.MerchantsTxn;
import com.npst.upi.portal.merchant.service.MerchantTxnService;
import com.npst.upi.portal.merchant.service.MerchantTxnServive;
import com.npst.upi.portal.merchant.service.impl.MerchantTxnServiceImpl;

//@Component
/*public class MerchantTxnReceiver implements MessageListener {
	private static final Logger	log	= LoggerFactory.getLogger(MerchantTxnReceiver.class);
	@Autowired
	private TaskExecutor		taskExecutor;
	
	@Autowired
	MerchantTxnService merchanttxnservice;
	
	@Override
	public void onMessage(Message message) {
		
		log.trace("MerchantTxnReq received from RabbitMQ");
		if (null != message) {
			try {
				Gson gson = new Gson();
				String messageJson = new String(message.getBody(), "UTF-8");
			  MerchantsTxn messagetxndto =gson.fromJson(messageJson, MerchantsTxn.class);
				taskExecutor.execute(()->{try {
					log.trace("Process Merchant Txn  recived from MQ");
					merchanttxnservice.processMerchantTxn(messagetxndto);
			} catch (Exception e) {
				log.error("Merchant Exception Occurred : {}", e);
			}});
			} catch (Exception e) {
				log.error("Merchant Application Exception Occurred : {}", e);
			}
		}
	}
}*/
