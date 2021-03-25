/**
 * 
 */
package com.npst.upi.portal.merchant.dto;

/**
 * @author Rahul Chaudhary
 *
 */
public enum ResponseStatus {
	
	SUCCESS("1", "1000", "Success"),
	ERROR("0", "2000", "Error"),
	MERCHANT_VPA_DOES_NOT_EXIST("0", "2001", "Merchant VPA does not exist"),
	MERCHANT_VPA_QR_IN_USED("0", "2001", "Merchant VPA already in used or Merchant already On-boarded"),
	MERCHANT_QR_GENERATE_UKN("0", "2001", "Something went wrong"),
	MERCHANT_SAS_DOES_NOT_EXIST("0", "2001", "Merchant SAS details not exist"),
	MERCHANT_QR_DETAILS_NOT_EXIST("0", "2001", "Merchant QR details not exist"),
	MERCHANT_QR_IN_USED("0", "2001", "Merchant QR is already in use"),
	MERCHANT_ACCOUNT_DOES_NOT_EXIST("0", "2001", "No details found");


	private final String status;
	private final String status_code;
	private final String status_msg;
	
	ResponseStatus(String status, String status_code, String status_msg) {
		this.status=status;
		this.status_code=status_code;
		this.status_msg=status_msg;
	}

	public String getStatus() {
		return status;
	}

	public String getStatus_code() {
		return status_code;
	}

	public String getStatus_msg() {
		return status_msg;
	}
	

}
