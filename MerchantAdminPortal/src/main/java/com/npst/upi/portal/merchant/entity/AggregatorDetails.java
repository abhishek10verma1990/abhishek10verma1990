/**
 * 
 */
package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author npst
 *
 */
@Entity
@Table(name="aggregator_details")
public class AggregatorDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="aggregator_id")
	private Long aggregatorId;
	
	@Column(name="aggunique_id")
	private String agguniqueId;
	
	@Column(name="aggregator_name")
	private String aggregatorName;
	
	@Column(name="encryption_key")
	private String encryptionKey;
	
	@Column(name="callbackurl_1")
	private String callbackurl1;

	@Column(name="callbackurl_2")
	private String callbackurl2;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name="is_active")
	private int isActive;

	public Long getAggregatorId() {
		return aggregatorId;
	}

	public void setAggregatorId(Long aggregatorId) {
		this.aggregatorId = aggregatorId;
	}

	public String getAgguniqueId() {
		return agguniqueId;
	}

	public void setAgguniqueId(String agguniqueId) {
		this.agguniqueId = agguniqueId;
	}

	public String getAggregatorName() {
		return aggregatorName;
	}

	public void setAggregatorName(String aggregatorName) {
		this.aggregatorName = aggregatorName;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public String getCallbackurl1() {
		return callbackurl1;
	}

	public void setCallbackurl1(String callbackurl1) {
		this.callbackurl1 = callbackurl1;
	}

	public String getCallbackurl2() {
		return callbackurl2;
	}

	public void setCallbackurl2(String callbackurl2) {
		this.callbackurl2 = callbackurl2;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
}
