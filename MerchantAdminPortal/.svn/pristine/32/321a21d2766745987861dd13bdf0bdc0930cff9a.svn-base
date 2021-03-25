package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchants_mode")
public class MerchantsMode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190211116385809898L;
	@Id
	private Long id;
	@Column(name = "env_type")
	private String envType;
	@Column(name = "access_key")
	private String accesskey;
	@Column(name = "is_active")
	private boolean active;
	@Column(name = "merchants_id")
	private String merchantsId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getEnvType() {
		return envType;
	}
	public void setEnvType(String envType) {
		this.envType = envType;
	}
	public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getMerchantsId() {
		return merchantsId;
	}
	public void setMerchantsId(String merchantsId) {
		this.merchantsId = merchantsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
 * CREATE TABLE `merchants_mode` (
 * 
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 * 
 * `env_type` enum('U','L') NOT NULL DEFAULT 'U',
 * 
 * `access_key` varchar(100) NOT NULL DEFAULT '',
 * 
 * `is_active` enum('0','1') NOT NULL DEFAULT '0',
 * 
 * `merchants_id` int(11) unsigned NOT NULL,
 * 
 * `user_id` int(11) NOT NULL,
 * 
 * `created_at` datetime NOT NULL,
 * 
 * `updated_at` datetime NOT NULL,
 * 
 * PRIMARY KEY (`id`)
 * 
 * )
 */