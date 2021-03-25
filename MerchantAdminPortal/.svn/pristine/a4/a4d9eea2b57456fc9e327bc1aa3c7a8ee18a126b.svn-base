package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MERCHANTS")
public class Merchants implements Serializable {

	private static final long serialVersionUID = 8605365783371361559L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MERCHANTS_TAB_ID_SEQ")
	@SequenceGenerator(name = "MERCHANTS_TAB_ID_SEQ", sequenceName = "MERCHANTS_TAB_ID_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "uniquemid")
	private String uniqueMid;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "name")
	private String name;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "pan_card")
	private String panCard;

	@Column(name = "is_active")
	private Boolean active;

	/*@Column(name = "additionalBhimQr")
	private int additionalBhimQr;*/
	/*
	 * @Column(name = "parent_merchants_id") private String parentMerchantsId;
	 */

	/*
	 * @Column(name = "user_id") private String userId;
	 */

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "merchant_code")
	private String merchantCode;

	/*
	 * @Column(name = "merchant_key") private String merchantKey;
	 */

	@Column(name = "aggunique_id")
	private String agguniqueId;

	@Column(name = "callback_type")
	private String callbackType;

	/*
	 * @Column(name = "request_origin") private String requestOrigin;
	 */

	@Column(name = "merchant_type")
	private String merchantType;

	private int status;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "merchant_org_name")
	private String merchantOrgName;

	//@Column(name = "merchant_vpa", unique = true, nullable = false)
	@Column(name = "merchant_vpa")
	private String merchantVPA;

	@Column(name = "merchant_mobile_no")
	private String merchantMobileNo;

	@Column(name = "merchant_account_no")
	private String merchantAccountNo;

	@Column(name = "merchant_IFSC_code")
	private String merchantIFSCCode;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "manager_name")
	private String managerName; /* Name of spoc */

	@Column(name = "merchant_callback_url")
	private String merchantCallbackURL;

	@Column(name = "merchant_address")
	private String merchantAddress;

	@Column(name = "mcccode")
	private String mccCode;

	@Column(name = "merchant_pan_number")
	private String merchantPanNumber;

	@Column(name = "org_code")
	private String orgCode;

	@Column(name = "settlement_bank")
	private String settlementBank;

	@Column(name = "api_bank")
	private String apiBank;

	@Column(name = "operator_contact_name")
	private String operatorContactName;

	@Column(name = "operator_mobile_number")
	private String operatorMobileNumber;

	@Column(name = "operator_email")
	private String operatorEmail;

	@Column(name = "aadhar_number")
	private String aadharNumber;

	@Column(name = "service_type")
	private int serviceType;

	@Column(name = "error_code")
	private String errorCode;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "remarks")
	private String remarks;
	@Column(name = "USER_FLAG")
	private int userFlag;
	@Column(name = "DELEVERED")
	private int delevered;
	@Column(name = "ADDITIONAL_MOB_NO")
	private String additionalMobileNo;

	// Add
	// @Column(name = "merchantVpa")
	// private List<String> merchantVpa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueMid() {
		return uniqueMid;
	}

	public void setUniqueMid(String uniqueMid) {
		this.uniqueMid = uniqueMid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public Integer getMccCodesId() { return mccCodesId; }
	 * 
	 * public void setMccCodesId(Integer mccCodesId) { this.mccCodesId = mccCodesId;
	 * }
	 * 
	 * 
	 * public String getSpocName() { return spocName; }
	 * 
	 * public void setSpocName(String spocName) { this.spocName = spocName; }
	 * 
	 * public String getContactEmail() { return contactEmail; }
	 * 
	 * public void setContactEmail(String contactEmail) { this.contactEmail =
	 * contactEmail; }
	 * 
	 * public String getSlug() { return slug; }
	 * 
	 * public void setSlug(String slug) { this.slug = slug; }
	 * 
	 * public String getWebsite() { return website; }
	 * 
	 * public void setWebsite(String website) { this.website = website; }
	 * 
	 * public String getMerchantRate() { return merchantRate; }
	 * 
	 * public void setMerchantRate(String merchantRate) { this.merchantRate =
	 * merchantRate; }
	 * 
	 * public String getCallbackServiceAddress() { return callbackServiceAddress; }
	 * 
	 * public void setCallbackServiceAddress(String callbackServiceAddress) {
	 * this.callbackServiceAddress = callbackServiceAddress; }
	 * 
	 * public Boolean getCallbackService() { return callbackService; }
	 * 
	 * public void setCallbackService(Boolean callbackService) {
	 * this.callbackService = callbackService; }
	 * 
	 * public Boolean getDocumentVerified() { return documentVerified; }
	 * 
	 * public void setDocumentVerified(Boolean documentVerified) {
	 * this.documentVerified = documentVerified; }
	 * 
	 * public Boolean getLive() { return live; }
	 * 
	 * public void setLive(Boolean live) { this.live = live; }
	 * 
	 */

	public String getPanCard() {
		return panCard;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/*
	 * public String getParentMerchantsId() { return parentMerchantsId; }
	 * 
	 * public void setParentMerchantsId(String parentMerchantsId) {
	 * this.parentMerchantsId = parentMerchantsId; }
	 */

	/*
	 * public String getUserId() { return userId; }
	 * 
	 * public void setUserId(String userId) { this.userId = userId; }
	 */

	/*public int getAdditionalBhimQr() {
		return additionalBhimQr;
	}*/

	public int getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(int userFlag) {
		this.userFlag = userFlag;
	}

	public int getDelevered() {
		return delevered;
	}

	public void setDelevered(int delevered) {
		this.delevered = delevered;
	}

	public String getAdditionalMobileNo() {
		return additionalMobileNo;
	}

	public void setAdditionalMobileNo(String additionalMobileNo) {
		this.additionalMobileNo = additionalMobileNo;
	}

	/*public void setAdditionalBhimQr(int additionalBhimQr) {
		this.additionalBhimQr = additionalBhimQr;
	}*/

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	/*
	 * public String getMerchantKey() { return merchantKey; }
	 * 
	 * public void setMerchantKey(String merchantKey) { this.merchantKey =
	 * merchantKey; }
	 */

	public String getAgguniqueId() {
		return agguniqueId;
	}

	public void setAgguniqueId(String agguniqueId) {
		this.agguniqueId = agguniqueId;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/*
	 * public MerchantsVpa getMerchantsVpa() { return merchantsVpa; } public void
	 * setMerchantsVpa(MerchantsVpa merchantsVpa) { this.merchantsVpa =
	 * merchantsVpa; } public MerchantsFd getMerchantsFd() { return merchantsFd; }
	 * public void setMerchantsFd(MerchantsFd merchantsFd) { this.merchantsFd =
	 * merchantsFd; } public MerchantsContact getMerchantsContact() { return
	 * merchantsContact; } public void setMerchantsContact(MerchantsContact
	 * merchantsContact) { this.merchantsContact = merchantsContact; }
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMerchantOrgName() {
		return merchantOrgName;
	}

	public void setMerchantOrgName(String merchantOrgName) {
		this.merchantOrgName = merchantOrgName;
	}

	public String getMerchantVPA() {
		return merchantVPA;
	}

	public void setMerchantVPA(String merchantVPA) {
		this.merchantVPA = merchantVPA;
	}

	public String getMerchantMobileNo() {
		return merchantMobileNo;
	}

	public void setMerchantMobileNo(String merchantMobileNo) {
		this.merchantMobileNo = merchantMobileNo;
	}

	public String getMerchantAccountNo() {
		return merchantAccountNo;
	}

	public void setMerchantAccountNo(String merchantAccountNo) {
		this.merchantAccountNo = merchantAccountNo;
	}

	public String getMerchantIFSCCode() {
		return merchantIFSCCode;
	}

	public void setMerchantIFSCCode(String merchantIFSCCode) {
		this.merchantIFSCCode = merchantIFSCCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getMerchantCallbackURL() {
		return merchantCallbackURL;
	}

	public void setMerchantCallbackURL(String merchantCallbackURL) {
		this.merchantCallbackURL = merchantCallbackURL;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getMerchantPanNumber() {
		return merchantPanNumber;
	}

	public void setMerchantPanNumber(String merchantPanNumber) {
		this.merchantPanNumber = merchantPanNumber;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSettlementBank() {
		return settlementBank;
	}

	public void setSettlementBank(String settlementBank) {
		this.settlementBank = settlementBank;
	}

	public String getApiBank() {
		return apiBank;
	}

	public void setApiBank(String apiBank) {
		this.apiBank = apiBank;
	}

	public String getOperatorContactName() {
		return operatorContactName;
	}

	public void setOperatorContactName(String operatorContactName) {
		this.operatorContactName = operatorContactName;
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

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	// public List<String> getMerchantVpa() {
	// return merchantVpa;
	// }
	// public void setMerchantVpa(List<String> merchantVpa) {
	// this.merchantVpa = merchantVpa;
	// }

}