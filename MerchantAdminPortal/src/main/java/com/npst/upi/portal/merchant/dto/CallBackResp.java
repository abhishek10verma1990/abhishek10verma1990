package com.npst.upi.portal.merchant.dto;

public class CallBackResp {
	private String merchId;
	private String merchantVpa;
	private String customerVpa;
	private String merchantTransactionId;
	private String transactionTimestamp;
	private String transactionAmount;
	private String gatewayTransactionId;
	private String gatewayResponseCode;
	private String gatewayResponseMessage;
	private String rrn;

	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public String getMerchantVpa() {
		return merchantVpa;
	}
	public void setMerchantVpa(String merchantVpa) {
		this.merchantVpa = merchantVpa;
	}
	public String getMerchantTransactionId() {
		return merchantTransactionId;
	}
	public void setMerchantTransactionId(String merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}
	public String getTransactionTimestamp() {
		return transactionTimestamp;
	}
	public void setTransactionTimestamp(String transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getGatewayTransactionId() {
		return gatewayTransactionId;
	}
	public void setGatewayTransactionId(String gatewayTransactionId) {
		this.gatewayTransactionId = gatewayTransactionId;
	}
	public String getGatewayResponseCode() {
		return gatewayResponseCode;
	}
	public void setGatewayResponseCode(String gatewayResponseCode) {
		this.gatewayResponseCode = gatewayResponseCode;
	}
	public String getGatewayResponseMessage() {
		return gatewayResponseMessage;
	}
	public void setGatewayResponseMessage(String gatewayResponseMessage) {
		this.gatewayResponseMessage = gatewayResponseMessage;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getCustomerVpa() {
		return customerVpa;
	}
	public void setCustomerVpa(String customerVpa) {
		this.customerVpa = customerVpa;
	}
}
