package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_master")
public class MerchantBankMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;	
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "vpa_handle")
	private String vpaHandle;
	@Column(name = "bank_url")
	private String bankUrl;
	@Column(name = "is_active")
	private String isActive;
	
	
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getVpaHandle() {
		return vpaHandle;
	}
	public void setVpaHandle(String vpaHandle) {
		this.vpaHandle = vpaHandle;
	}
	public String getBankUrl() {
		return bankUrl;
	}
	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
