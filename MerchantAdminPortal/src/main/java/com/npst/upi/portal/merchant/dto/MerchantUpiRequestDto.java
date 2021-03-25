package com.npst.upi.portal.merchant.dto;

public class MerchantUpiRequestDto {

	private String orgName;
	private String orgCode;
	private String mcccode;
	private String panNumber;
	private String aadhar;
	private String settlementBank;
	private String bankAccountNo;
	private String ifscCode;
	private String managerName;
	private String email;
	private String mobile;
	private String address;
	private String contactName;
	private String operatorMobileNumber;
	private String operatorEmail;
	private String callbackURL;
	private String apiBank;
	private String vpa;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getSettlementBank() {
		return settlementBank;
	}

	public void setSettlementBank(String settlementBank) {
		this.settlementBank = settlementBank;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getOperatorMobileNumber() {
		return operatorMobileNumber;
	}

	public void setOperatorMobileNumber(String operatorMobileNumber) {
		this.operatorMobileNumber = operatorMobileNumber;
	}

	public String getOperatorEmail() {
		return operatorEmail;
	}

	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}

	public String getCallbackURL() {
		return callbackURL;
	}

	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	public String getApiBank() {
		return apiBank;
	}

	public void setApiBank(String apiBank) {
		this.apiBank = apiBank;
	}

	public String getVpa() {
		return vpa;
	}

	public void setVpa(String vpa) {
		this.vpa = vpa;
	}

	public String getMcccode() {
		return mcccode;
	}

	public void setMcccode(String mcccode) {
		this.mcccode = mcccode;
	}

	@Override
	public String toString() {
		return "MerchantUpiRequestDto [orgName=" + orgName + ", orgCode=" + orgCode + ", mcccode=" + mcccode
				+ ", panNumber=" + panNumber + ", aadhar=" + aadhar + ", settlementBank=" + settlementBank
				+ ", bankAccountNo=" + bankAccountNo + ", ifscCode=" + ifscCode + ", managerName=" + managerName
				+ ", email=" + email + ", mobile=" + mobile + ", address=" + address + ", contactName=" + contactName
				+ ", operatorMobileNumber=" + operatorMobileNumber + ", operatorEmail=" + operatorEmail
				+ ", callbackURL=" + callbackURL + ", apiBank=" + apiBank + ", vpa=" + vpa + "]";
	}

}
