package com.npst.upi.portal.merchant.constant;
/**
 * 
 * @author npst
 *
 */
public enum MerchantRegStatus {
	NEW_MERCHANT(0,"New Merchant"),
	PENDING_MERCHANT(1,"Pending Merchant"),
	SUCCESS_MERCHANT(2,"Successfully Created Merchant"),
	FAILURE_MERCHANT(3,"Fail To Create Merchant "),
	NEW_FILE_UPLOADED(0,"File uploaded successfully on server, waiting to proccess"),
	FILE_IN_PROCCESS(1,"File is under proccess"),
	FILE_SUCCESSFULLY_PROCESSED(2,"Successfully Proccessed"),
	FILE_FAILURE(3,"Application Exception Occurred"),
	FILE_INVALID(4,"Invalid file data or columns for merchant"),
	FILE_SHEDULED_FOR_NEXT_PROCESS(5,"File sheduled for next process ,please check status later"),
	FAILED(0, "Failed"), 
	SUCCESS_STATE_1(1, "Merchant Switch Success"), 
	SUCCESS_STATE_2(2, "UPI Switch Success"), 
	SUCCESS_STATE_3(3, "QR Code Success"),
	FAILURE_STATE_4(4, "Fail At UPI");
	
	private MerchantRegStatus(int code,String detail){
		this.status=code;
		this.description=detail;
	}
	private int status;
	private String description;
	public int getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
	}
	
}
