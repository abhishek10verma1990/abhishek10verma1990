package com.npst.upi.portal.merchant.utility;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.npst.upi.portal.merchant.dto.MerchantsTxn;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;

@Component
public class MerchantSMS {
Merchants merchant=null;
@Autowired
MerchantsRepo merchantrepo;

Merchants merchantent=null;
String message = "";

	private static final Logger	log	= LoggerFactory.getLogger(MerchantSMS.class);


	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	String strDate= formatter.format(new Date());
	
	public void processSms(MerchantsTxn merchantTxnReq) {
		try {
		
			
			List<Merchants> merchantlist= merchantrepo.findFirstByMerchantVPA(merchantTxnReq.getPayeeAdd());
			
			if (merchantlist != null && merchantlist.size() > 0) {
				merchantent=merchantlist.get(merchantlist.size() - 1);
			
			}
			else {
				log.info("no Records found in Merchant for {} : ",merchantTxnReq.getPayeeAdd() );
				return;
			}
		if(merchantent.getContactNo()!="") {
			log.trace("Process for Merchant SMS on {}",merchantent.getContactNo());
			message = Utility.getProperty("BHIM_QR_SMS");
			if(message!=null) {
				message=MessageFormat.format(message, Util.maskNumber(merchantTxnReq.getPayeeAccountNo()),merchantTxnReq.getPayeeAdd(),merchantTxnReq.getAmount(),strDate,Util.maskNumber(merchantTxnReq.getPayerAccountNo()),merchantTxnReq.getCustomerRefId());
				log.debug("Final SMS to send {}",message);
				Util.sendSMS(message,"91"+merchantent.getContactNo(),Constants.SMS);	
			}
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	
}
