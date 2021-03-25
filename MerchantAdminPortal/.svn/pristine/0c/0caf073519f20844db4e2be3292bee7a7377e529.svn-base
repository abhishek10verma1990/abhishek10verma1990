package com.npst.upi.portal.merchant.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.dao.MerchantNotificationDao;
import com.npst.upi.portal.merchant.entity.MerchantNotificaion;
import com.npst.upi.portal.merchant.repo.MerchantNotificationRepo;
@Service
public class MerchantNotificationDaoImpl implements MerchantNotificationDao {

@Autowired
MerchantNotificationRepo merchantnotirepo;
	
	@Override
	public void storeNofification(String objResponse, String txnId, String finalcallbackresp,String responseCode) {
		try {
			

		MerchantNotificaion merchatnoti=new MerchantNotificaion();
		merchatnoti.setMerchantTxnId(txnId);
		merchatnoti.setRequest(finalcallbackresp);
		merchatnoti.setStatus(responseCode);
		merchatnoti.setCreatedat(new Date());
		merchatnoti.setResponce(objResponse);
		merchantnotirepo.save(merchatnoti);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
