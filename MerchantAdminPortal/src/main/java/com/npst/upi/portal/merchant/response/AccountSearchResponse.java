package com.npst.upi.portal.merchant.response;

import com.npst.upi.portal.merchant.dto.CustomerdetailsResponse;

public class AccountSearchResponse {

	CustomerdetailsResponse customer_details;

	public CustomerdetailsResponse getCustomer_details() {
		return customer_details;
	}

	public void setCustomer_details(CustomerdetailsResponse customer_details) {
		this.customer_details = customer_details;
	}

	@Override
	public String toString() {
		return "AccountSerachResponse [customer_details=" + customer_details + "]";
	}
}
