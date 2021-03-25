package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mcc_codes")
public class MccCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012551850119277940L;

	@Id
	private Long id;	
	@Column(name = "code")
	private String code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "mcc_category")
	private String mccCategory;
	
	@Column(name = "is_active")
	private String isActive;
	
	@Column(name = "created_at")
	private String createdAt;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMccCategory() {
		return mccCategory;
	}

	public void setMccCategory(String mccCategory) {
		this.mccCategory = mccCategory;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
