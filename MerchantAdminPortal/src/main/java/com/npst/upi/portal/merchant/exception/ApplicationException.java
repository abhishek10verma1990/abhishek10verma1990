package com.npst.upi.portal.merchant.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = -2459676900005296981L;
	private String strNewErrorCode = "";

	public ApplicationException(final String errorMessage) {
		super(errorMessage);
	}

	public ApplicationException(final String errorCode, final String errorMessage) {
		super(errorMessage);
		this.strNewErrorCode = errorCode;
	}

	public ApplicationException(final Throwable t, final String errorMessage) {
		super(t);
		strNewErrorCode = errorMessage;
	}

	public String getErrorCode() {
		if (strNewErrorCode.equals("")) {
			return super.getMessage();
		}
		return strNewErrorCode;
	}

	public void setErrorCode(final String errorCode) {
		strNewErrorCode = errorCode;
	}

	@Override
	public String toString() {
		return "ApplicationException [strNewErrorCode=" + strNewErrorCode + "]";

	}

}
