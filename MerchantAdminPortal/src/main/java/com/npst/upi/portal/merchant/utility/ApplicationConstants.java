package com.npst.upi.portal.merchant.utility;

public enum ApplicationConstants {
	SERVER_BUSY("1503", "SERVER_BUSY", "plz try later"),
	EXCEPTION("100", "Exception", "Some exception occurred"),
	APPLICATION_EXCEPTION("101", "Application Exception", "Some application exception occurred"),
	GLOBAL_EXCEPTION("102", "Global Exception", "Some global exception occurred"),
	INVALID_DATA("103", "Invalid Data", "Invalid request data"),
	MERCHANTVPA_ALREADY_EXISTS("104", "Merchant VPA alredy exists", "Merchant VPA already exists"),
	NO_DATA("105", "No data found", "No data found"),
	MODULE_ALREADY_EXISTS("106", "Module alredy exists", "Module already exists"),
	INAPPROPRIATE_PARAMS("107", "Inappropriate parameters", "Inappropriate parameters"),
	PERSISTANCE_FAIL("108", "Kindy try again", "Kindy try again"),
	MODULE_NOT_EXISTS("109", "No Such module exists", "No Such module exists"),
	ROLE_ALREADY_EXISTS("110", "Role already exists", "Role already exists"),
	USER_ALREADY_EXISTS("111","User already exists","User already exists"),
	ROLE_NOT_EXISTS("112", "No such role exists", "No such role exists"),
	ROLE_NOT_DELETED("113", "Role already attached with user so can not be deleted.", "Role already attached with user so can not be deleted."),
	GROUP_NOT_EXISTS("114", "Group does not exist", "Group does not exist"),
	UNSUCCESSFULL_OPERATION("115", "Operation was unsuccessfull", "Operation was unsuccessfull"),
	OPERATION_NAME("116", "Operation with this name already exist", "Operation with this name already exist"),
	OPERATION_KEY("117", "Operation with this key already exist", "Operation with this key already exist"),
	OPERATION_NOT_EXISTS("118", "No such operation exists", "No such operation exists"),
	OPERATION_ALREADY_EXISTS("119", "Operation already exists", "Operation already exists"),
	MODULE_NAME("120", "Module with this name already exist", "Module with this name already exist"),
	MODULE_KEY("121", "Module with this key already exist", "Module with this key already exist"),
	USER_NOT_FOUND("122", "User does not exists", "User does not exists"),
	PASSWORD_POLICY_BREACH("123","Password Policy Breach","Password Policy Breach"),
	INVALID_PASSWORD("124", "Invalid Password", "Invalid Password"),
	USER_LOCKED("125","User is Locked","User is Locked"),
	NON_ACTIVE_USER("126","User is Locked","Due to Inactive Account user is Locked"),
	PASSWORD_RESET_TIME_EXCEED("127","User is Locked","User is locked due to Minimum days for password Reset Limit Exceed"),
	CSV_FILE_PATTERN("128","Rename the File","CSV File name pattern does not match please rename the file"),
	DUPLICATE_FILE_PATTERN("129","Dublicate file Upload","Dublicate file Upload"),
	INVALID_FILE_TYPE("130","Invalid File Type","Kindly Upload a valid CSV File"),
	FILE_PARAMETER("140","File parameter","Uploaded file column names are not as per specification"),
	INVALID_VPA("1104", "Invalid VPA ", "Invalid vpa id plz try with different");
	private String errorCode;
	private String errorReason;
	private String errorDetails;

	private ApplicationConstants(String errorCode, String errorReason, String errorDetails) {
		this.errorCode = errorCode;
		this.errorReason = errorReason;
		this.errorDetails = errorDetails;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public String getErrorDetails() {
		return errorDetails;
	}
}