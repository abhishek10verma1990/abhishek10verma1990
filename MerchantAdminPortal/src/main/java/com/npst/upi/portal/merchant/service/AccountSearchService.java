package com.npst.upi.portal.merchant.service;

import com.npst.upi.portal.merchant.dto.AccountSearchRequestDto;
import com.npst.upi.portal.merchant.dto.CommonRequest;
import com.npst.upi.portal.merchant.dto.UpdateAccountDetailsDto;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.response.CommonResponse;
import com.npst.upi.portal.merchant.response.UpdateAccountDetailsResponse;

public interface AccountSearchService {

	UpdateAccountDetailsResponse updateAccountDetails(UpdateAccountDetailsDto requestDto);
	//Merchants getAccountDetails(AccountSearchRequestDto accountSearchRequestDto);
	
	Merchants getAccountDetails(String Message, AccountSearchRequestDto accountSearchRequestDto);

	void Setparam(Merchants accountSerachResponse, AccountSearchRequestDto accountSearchRequestDto);

}
