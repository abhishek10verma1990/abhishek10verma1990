package com.npst.upi.portal.merchant.dto;

import java.util.List;

import com.npst.upi.portal.merchant.entity.Merchants;

public class MerchantDetailsDTO {

	private List<Merchants> merchantsList;

	public List<Merchants> getMerchantsList() {
		return merchantsList;
	}

	public void setMerchantsList(List<Merchants> merchantsList) {
		this.merchantsList = merchantsList;
	}

	public MerchantDetailsDTO(List<Merchants> merchantsList) {
		super();
		this.merchantsList = merchantsList;
	}

}
