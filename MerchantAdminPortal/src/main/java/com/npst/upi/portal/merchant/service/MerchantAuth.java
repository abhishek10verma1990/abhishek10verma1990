package com.npst.upi.portal.merchant.service;

import java.util.List;

import com.npst.upi.portal.merchant.dto.AuthorizerDto;
import com.npst.upi.portal.merchant.dto.MerchantStatusDto;
import com.npst.upi.portal.merchant.entity.Merchants;

public interface MerchantAuth {

	public List<AuthorizerDto> getAuthorizerData();
	public List<MerchantStatusDto> UpdateMerchantStatus(MerchantStatusDto statusDto);
}
