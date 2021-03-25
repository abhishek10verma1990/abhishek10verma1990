package com.npst.upi.portal.merchant.service;

import java.util.List;

import com.npst.upi.portal.merchant.dto.MerchantRegistrationUpdateRequest;
import com.npst.upi.portal.merchant.dto.MerchantsAndStatusDetailsdto;
import com.npst.upi.portal.merchant.dto.MerchantsDto;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.exception.ApplicationException;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
public interface MerchantsService {
	public Integer insert(List<Merchants> vpaBulkUpload);
   // List<Merchants> loadAllCustomers();
    boolean insert(Merchants merchantsList) throws ApplicationException;
    void insertMerchantErrorLogs(Merchants merchant,String errorCode);
	public Merchants findByMerchantVPAAndMerchantType(String vpa, String merchantType);
	public int updateMerchantReg(MerchantRegistrationUpdateRequest request);
	public Merchants findByMerchantVPAAndMerchantTypeAndActive(String vpa, String merchantType, Boolean active);
	//public Merchants findBymerchantAccountNo(String accountnum);
	
	List<MerchantsAndStatusDetailsdto> loadAllCustomers();
}