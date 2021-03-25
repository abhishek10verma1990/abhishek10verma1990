package com.npst.upi.portal.merchant.dto;

import java.util.Date;

public class SASReq {
	private String VENDOR_ID;
	private String METHOD_ID;
	private String REQUEST_ENC;
	private String REQUEST_DIGEST;
	
	private String REQUEST_DATE_TIME;
	
	public String getVENDOR_ID() {
		return VENDOR_ID;
	}
	public void setVENDOR_ID(String vENDOR_ID) {
		VENDOR_ID = vENDOR_ID;
	}
	public String getMETHOD_ID() {
		return METHOD_ID;
	}
	public void setMETHOD_ID(String mETHOD_ID) {
		METHOD_ID = mETHOD_ID;
	}
	public String getREQUEST_ENC() {
		return REQUEST_ENC;
	}
	public void setREQUEST_ENC(String rEQUEST_ENC) {
		REQUEST_ENC = rEQUEST_ENC;
	}
	public String getREQUEST_DIGEST() {
		return REQUEST_DIGEST;
	}
	public void setREQUEST_DIGEST(String rEQUEST_DIGEST) {
		REQUEST_DIGEST = rEQUEST_DIGEST;
	}
	
	public String getREQUEST_DATE_TIME() {
		return REQUEST_DATE_TIME;
	}
	public void setREQUEST_DATE_TIME(String rEQUEST_DATE_TIME) {
		REQUEST_DATE_TIME = rEQUEST_DATE_TIME;
	}
	@Override
	public String toString() {
		return "SASReq [VENDOR_ID=" + VENDOR_ID + ", METHOD_ID=" + METHOD_ID + ", REQUEST_ENC=" + REQUEST_ENC
				+ ", REQUEST_DIGEST=" + REQUEST_DIGEST + ", REQUEST_DATE_TIME=" + REQUEST_DATE_TIME + "]";
	}
		
	
}
