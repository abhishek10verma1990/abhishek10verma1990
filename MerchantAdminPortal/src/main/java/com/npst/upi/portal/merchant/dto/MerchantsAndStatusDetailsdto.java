package com.npst.upi.portal.merchant.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


public class MerchantsAndStatusDetailsdto {

	private static final long serialVersionUID = 1L;
	
	private String merchantsAccountno;
	private String mccCode;
	private String merchantsMobileNo;
	private String merchantsType;
	private String merchantsVpa;
	private String merchantsRemark;
	private String merchantsName;
	private int status;
	private String customerId;
	private String merchantsDelivery;
	private String additionalMobileNo;
	private int userFlag;
	private String merchantsAddress;
	private String statusvalue;
	
	public String getMerchantsAccountno() {
		return merchantsAccountno;
	}
	public void setMerchantsAccountno(String merchantsAccountno) {
		this.merchantsAccountno = merchantsAccountno;
	}
	public String getMccCode() {
		return mccCode;
	}
	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}
	public String getMerchantsMobileNo() {
		return merchantsMobileNo;
	}
	public void setMerchantsMobileNo(String merchantsMobileNo) {
		this.merchantsMobileNo = merchantsMobileNo;
	}
	public String getMerchantsType() {
		return merchantsType;
	}
	public void setMerchantsType(String merchantsType) {
		this.merchantsType = merchantsType;
	}
	public String getMerchantsVpa() {
		return merchantsVpa;
	}
	public void setMerchantsVpa(String merchantsVpa) {
		this.merchantsVpa = merchantsVpa;
	}
	public String getMerchantsRemark() {
		return merchantsRemark;
	}
	public void setMerchantsRemark(String merchantsRemark) {
		this.merchantsRemark = merchantsRemark;
	}
	public String getMerchantsName() {
		return merchantsName;
	}
	public void setMerchantsName(String merchantsName) {
		this.merchantsName = merchantsName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMerchantsDelivery() {
		return merchantsDelivery;
	}
	public void setMerchantsDelivery(String merchantsDelivery) {
		this.merchantsDelivery = merchantsDelivery;
	}
	public String getAdditionalMobileNo() {
		return additionalMobileNo;
	}
	public void setAdditionalMobileNo(String additionalMobileNo) {
		this.additionalMobileNo = additionalMobileNo;
	}
	public int getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(int userFlag) {
		this.userFlag = userFlag;
	}
	public String getMerchantsAddress() {
		return merchantsAddress;
	}
	public void setMerchantsAddress(String merchantsAddress) {
		this.merchantsAddress = merchantsAddress;
	}
	public String getStatusvalue() {
		return statusvalue;
	}
	public void setStatusvalue(String statusvalue) {
		this.statusvalue = statusvalue;
	}
	
	@Override
	public String toString() {
		return "MerchantsAndStatusDetailsdto [merchantsAccountno=" + merchantsAccountno + ", mccCode=" + mccCode
				+ ", merchantsMobileNo=" + merchantsMobileNo + ", merchantsType=" + merchantsType + ", merchantsVpa="
				+ merchantsVpa + ", merchantsRemark=" + merchantsRemark + ", merchantsName=" + merchantsName
				+ ", status=" + status + ", customerId=" + customerId + ", merchantsDelivery=" + merchantsDelivery
				+ ", additionalMobileNo=" + additionalMobileNo + ", userFlag=" + userFlag + "]";
	}
}
