/**
 * 
 */
package com.npst.upi.portal.merchant.service;

import com.npst.upi.portal.merchant.dto.QRCodeRequest;
import com.npst.upi.portal.merchant.entity.Merchants;

/**
 * @author Rahul Chaudhary
 *
 */
public interface QrCodeService {

	void generateQr(QRCodeRequest qrcoderequest) throws Exception;
	String generateSASQr(String qrcoderequest) throws Exception;

	String downloadQr(QRCodeRequest qrcoderequest) throws Exception;
	
	void generateQr(Merchants merchant) throws Exception;
	String downloadSASQr(String qrcoderequest,String branchcode) throws Exception;
	

}
