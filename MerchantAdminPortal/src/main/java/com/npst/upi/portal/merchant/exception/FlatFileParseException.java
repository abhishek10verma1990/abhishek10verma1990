package com.npst.upi.portal.merchant.exception;

public class FlatFileParseException extends Exception {
	private static final long serialVersionUID = 1L;
	private String strNewErrorCode = "";

	public FlatFileParseException(final String errorMessage) {
		super(errorMessage);
	}

	public FlatFileParseException(final String errorCode, final String errorMessage) {
		super(errorMessage);
		this.strNewErrorCode = errorCode;
	}

	public FlatFileParseException(final Throwable t, final String errorMessage) {
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
