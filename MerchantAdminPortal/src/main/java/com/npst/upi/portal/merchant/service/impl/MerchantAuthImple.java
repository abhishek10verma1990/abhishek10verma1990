package com.npst.upi.portal.merchant.service.impl;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.AuthorizerDto;
import com.npst.upi.portal.merchant.dto.MerchantStatusDto;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.service.MerchantAuth;

@Service
public class MerchantAuthImple implements MerchantAuth{
	
    @Autowired
	MerchantsRepo merchantRepo;
	@Autowired
    AuthorizerDto authdto;
    
	@Override
	public List<AuthorizerDto> getAuthorizerData() {
		
	 List<Object[]> objectsList = merchantRepo.getByStatus();
	 List<AuthorizerDto> list =new ArrayList<>();
	 
	 for(Object[] object: objectsList) {
		   AuthorizerDto authdto=new AuthorizerDto();
		 authdto.setStatuscode(object[5]==null?0:Integer.parseInt(object[5].toString()));
		 authdto.setStatusvalue(object[4]==null?null:(String)object[4]);
		 authdto.setMerhantname(object[1]==null?null:(String)object[1]);
		 authdto.setMerchantAccountNo(object[2]==null?null:(String)object[2]);
		 authdto.setMerchantVPA(object[3]==null?null:(String)object[3]);
		 list.add(authdto);
	 }
		return list;
	}
	
	@Override
	public List<MerchantStatusDto> UpdateMerchantStatus(MerchantStatusDto statusDto) {
		List<MerchantStatusDto> dto = new ArrayList<MerchantStatusDto>();
		
		List<Merchants> obj = merchantRepo.findByMerchantVpaAndMerchantAccountNum(statusDto.getMerchantVPA(),statusDto.getMerchantAccountNo());
		if(obj!=null){
			for (Merchants merchants : obj) {
				
			
			if(statusDto.getRequestType()==Constants.REJECTED)
			{
				merchants.setStatus(Constants.REJECTED);
				merchants.setRemarks(statusDto.getRemarks());
				
			}
			else if(statusDto.getRequestType()==Constants.REVERTED)
			{
				merchants.setStatus(Constants.REVERTED);
				merchants.setRemarks(statusDto.getRemarks());
			}
			else if(statusDto.getRequestType()==Constants.QR_APPROVED)
			{
				merchants.setStatus(Constants.QR_APPROVED);
				merchants.setRemarks(statusDto.getRemarks());
			}
			
			try {
				merchantRepo.save(merchants);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return null;
		}
}
