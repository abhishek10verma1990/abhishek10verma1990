package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "merchants_fd")
public class MerchantsFd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -227519122062547849L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="merchant_detail")
	private String merchantDetail;
	private boolean active;
	@Column(name = "is_default")
	private boolean isdefault;
	/*@Column(name = "merchants_id")
	private String merchantsId;*/
	@Column(name = "vpa_id")
	private String vpaId;
	@Column(name = "txn_limit")
	private String txnLimit;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Merchants merchants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getMerchantDetail() {
		return merchantDetail;
	}

	public void setMerchantDetail(String merchantDetail) {
		this.merchantDetail = merchantDetail;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isIsdefault() {
		return isdefault;
	}

	public void setIsdefault(boolean isdefault) {
		this.isdefault = isdefault;
	}
/*
	public String getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(String merchantsId) {
		this.merchantsId = merchantsId;
	}*/

	public String getVpaId() {
		return vpaId;
	}

	public void setVpaId(String vpaId) {
		this.vpaId = vpaId;
	}

	public String getTxnLimit() {
		return txnLimit;
	}

	public void setTxnLimit(String txnLimit) {
		this.txnLimit = txnLimit;
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

}
/*
 * CREATE TABLE `merchants_fd` (
 * 
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * 
 * `merchant_detail` varchar(2085) DEFAULT NULL,
 * 
 * `is_active` int(2) DEFAULT NULL,
 * 
 * `is_default` int(2) DEFAULT NULL,
 * 
 * `merchants_id` int(11) DEFAULT NULL,
 * 
 * `vpa_id` int(11) DEFAULT NULL,
 * 
 * `txn_limit` varchar(45) DEFAULT NULL,
 * 
 * `created_at` datetime DEFAULT NULL,
 * 
 * `updated_at` datetime DEFAULT NULL,
 * 
 * PRIMARY KEY (`id`),
 * 
 * UNIQUE KEY `vpa_id_UNIQUE` (`vpa_id`)
 * 
 * ) ENGINE=InnoDB AUTO_INCREMENT=340 DEFAULT CHARSET=latin1;
 * 
 */