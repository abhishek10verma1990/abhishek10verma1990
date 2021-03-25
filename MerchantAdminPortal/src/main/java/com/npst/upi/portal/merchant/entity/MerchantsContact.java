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
@Table(name = "merchants_contact")
public class MerchantsContact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4656160215788025745L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(name = "contact_no")
	private String contactNo;
	private String email;
	@Column(name = "is_active")
	private boolean active;
	@Column(name = "is_sms")
	private boolean sms;
	@Column(name = "is_email")
	private boolean isemail;

	/*@Column(name = "merchants_id")
	private String merchantsId;*/
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public boolean isIsemail() {
		return isemail;
	}

	public void setIsemail(boolean isemail) {
		this.isemail = isemail;
	}

	/*public String getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(String merchantsId) {
		this.merchantsId = merchantsId;
	}*/

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
 * CREATE TABLE `merchants_contact` (
 * 
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 * 
 * `name` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
 * 
 * `contact_no` varchar(12) NOT NULL DEFAULT '',
 * 
 * `email` varchar(255) NOT NULL DEFAULT '',
 * 
 * `is_active` enum('0','1') NOT NULL,
 * 
 * `is_sms` enum('0','1') DEFAULT NULL,
 * 
 * `is_email` enum('0','1') DEFAULT NULL,
 * 
 * `merchants_id` int(11) unsigned DEFAULT NULL,
 * 
 * `created_at` datetime NOT NULL,
 * 
 * `updated_at` datetime NOT NULL,
 * 
 * PRIMARY KEY (`id`),
 * 
 * KEY `merchants_cont_idfkx` (`merchants_id`),
 * 
 * CONSTRAINT `merchants_cont_idfkx` FOREIGN KEY (`merchants_id`) REFERENCES
 * `merchants` (`id`)
 * 
 * )
 */