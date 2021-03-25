package com.npst.upi.portal.merchant.dto;

import java.util.List;

import com.npst.upi.portal.merchant.entity.MccCode;
import com.npst.upi.portal.merchant.entity.Merchants;

public class MerchantRespDto {
	private List<Merchants> merchantDetails;

	public List<Merchants> getMerchantDetails() {
		return merchantDetails;
	}

	public MerchantRespDto(List<Merchants> merchantDetails) {
		super();
		this.merchantDetails = merchantDetails;
	}

	public void setMerchantDetails(List<Merchants> merchantDetails) {
		this.merchantDetails = merchantDetails;
	}
	
	

}
