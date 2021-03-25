/**
 * 
 */
package com.npst.upi.portal.merchant.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.entity.Merchants;

/**
 * @author Rahul Chaudhary
 *
 */
@Component
public class UpiQrCodeGenerator {
	

	private String refUrl = Constants.REF_URL;
	private static final Logger log = LoggerFactory.getLogger(UpiQrCodeGenerator.class);
	
	@Autowired
	private QrGeneratorUtil qrutil;

	public void generateQr(Merchants merchant) {
		String filePath;
		try {
			QrGeneratorUtil qrutil=new QrGeneratorUtil();
			filePath = qrutil.generateFilePath(merchant);
			log.info("File Path is{}",filePath);
			String fileName = qrutil.generateFileName(merchant);
			log.info("File Name is{}",fileName);
			qrutil.generateQr(merchant, qrstringDefination(merchant), filePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
	}

	private String qrstringDefination(Merchants merchant) {
		final String qrstr = "upi://pay?pa=" + merchant.getMerchantVPA() + "&pn=" + merchant.getMerchantOrgName() + "&mc=" + merchant.getMccCode()
		+ "&tr=" + "1234567887654321" + "&tn="+"Pay to Merchant "+ "&am=" + "0" + "&mam=" + "0" + "&cu=" + "INR" + "&refUrl=" + refUrl;
		log.info("qrstr : {}", qrstr);
		return qrstr;
	}

}
