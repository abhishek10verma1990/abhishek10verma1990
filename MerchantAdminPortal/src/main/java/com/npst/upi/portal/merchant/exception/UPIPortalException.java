package com.npst.upi.portal.merchant.exception;

public class UPIPortalException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strNewErrorCode = "";

	public UPIPortalException(String strException) {
		super(strException);
	}

	public UPIPortalException(String strException, String errorCode) {
		super(strException);
		this.strNewErrorCode = errorCode;
	}

	public UPIPortalException(String strException, Throwable t) {
		super(t);
		strNewErrorCode = strException;

	}

	public String getErrorCode() {
		if (strNewErrorCode.equals("")) {
			return super.getMessage();
		}
		return strNewErrorCode;
	}

	public void setErrorCode(String strErrorCode) {
		strNewErrorCode = strErrorCode;
	}
}
