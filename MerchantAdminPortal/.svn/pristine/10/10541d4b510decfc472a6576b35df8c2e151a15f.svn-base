package com.npst.upi.portal.merchant.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "respmerchant")
public class MerchantUpiResponseEntity implements Serializable {
	private static final long serialVersionUID = -8575494891886142297L;
	@Id
	@GeneratedValue
	private long id;
	private String merchantId;
	private Date respDate;
	private String isUserSavedSuccessfully;
	private String uniquemid;
	private String mccCodesId;
	private String name;
	private String contactEmail;
	private String contactNo;
	private String merchantPrivateKey;
	private String merchantPublicKey;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Date getRespDate() {
		return respDate;
	}

	public void setRespDate(Date respDate) {
		this.respDate = respDate;
	}

	public String getIsUserSavedSuccessfully() {
		return isUserSavedSuccessfully;
	}

	public void setIsUserSavedSuccessfully(String isUserSavedSuccessfully) {
		this.isUserSavedSuccessfully = isUserSavedSuccessfully;
	}

	public String getUniquemid() {
		return uniquemid;
	}

	public void setUniquemid(String uniquemid) {
		this.uniquemid = uniquemid;
	}

	public String getMccCodesId() {
		return mccCodesId;
	}

	public void setMccCodesId(String mccCodesId) {
		this.mccCodesId = mccCodesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getMerchantPrivateKey() {
		return merchantPrivateKey;
	}

	public void setMerchantPrivateKey(String merchantPrivateKey) {
		this.merchantPrivateKey = merchantPrivateKey;
	}

	public String getMerchantPublicKey() {
		return merchantPublicKey;
	}

	public void setMerchantPublicKey(String merchantPublicKey) {
		this.merchantPublicKey = merchantPublicKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MerchantUpiResponseEntity [id=" + id + ", merchantId=" + merchantId + ", respDate=" + respDate
				+ ", isUserSavedSuccessfully=" + isUserSavedSuccessfully + ", uniquemid=" + uniquemid + ", mccCodesId="
				+ mccCodesId + ", name=" + name + ", contactEmail=" + contactEmail + ", contactNo=" + contactNo
				+ ", merchantPrivateKey=" + merchantPrivateKey + ", merchantPublicKey=" + merchantPublicKey
				+ ", status=" + status + "]";
	}

}
