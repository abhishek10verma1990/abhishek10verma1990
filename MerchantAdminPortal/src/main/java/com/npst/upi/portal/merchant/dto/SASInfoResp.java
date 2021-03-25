package com.npst.upi.portal.merchant.dto;

import javax.persistence.Column;

public class SASInfoResp {
	

	
	private String username;

	private String stNo;
	
	private String request;
	
	private String response;
	
	private String loginToken;
	
	private String descCode;
	
	private String loginDate;
	
	private Integer userFlag;
	private String dpcd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStNo() {
		return stNo;
	}

	public void setStNo(String stNo) {
		this.stNo = stNo;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getDescCode() {
		return descCode;
	}

	public void setDescCode(String descCode) {
		this.descCode = descCode;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public String getDpcd() {
		return dpcd;
	}

	public void setDpcd(String dpcd) {
		this.dpcd = dpcd;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	

	public Integer getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}

	@Override
	public String toString() {
		return "SASInfoResp [username=" + username + ", stNo=" + stNo + ", request=" + request + ", response="
				+ response + ", loginToken=" + loginToken + ", descCode=" + descCode + ", loginDate=" + loginDate
				+ ", userFlag=" + userFlag + ", dpcd=" + dpcd + "]";
	}

	
	


}
