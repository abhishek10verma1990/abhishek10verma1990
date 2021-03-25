package com.npst.upi.portal.merchant;

import com.npst.upi.portal.merchant.core.UpiQrCodeGenerator;
import com.npst.upi.portal.merchant.entity.Merchants;

public class GenerateQR {

	public static void main(String[] args) {
		Merchants merchant=new Merchants();
		merchant.setBranchCode("100");
		merchant.setMerchantAccountNo("100100101427");
		merchant.setMerchantVPA("9867061659.timepay@cosmos");
		merchant.setMccCode("5912");
		merchant.setMerchantOrgName("MEDEASY PHARMAC");
		merchant.setName("MEDEASY PHARMAC");
		UpiQrCodeGenerator obj=new UpiQrCodeGenerator();
		obj.generateQr(merchant);
	}
	
}
