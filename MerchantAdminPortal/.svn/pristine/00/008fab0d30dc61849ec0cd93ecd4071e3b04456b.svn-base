package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchant_notification")
public class MerchantNotificaion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="merchant_txn_id")
	private String merchantTxnId;
	
	@Column(name="status")
	private String status;

	@Column(name="req_body")
	private String Request;
	
	@Column(name="resp_body")
	private String Responce;

	@Column(name="created_at")
	private Date createdat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMerchantTxnId() {
		return merchantTxnId;
	}

	public void setMerchantTxnId(String merchantTxnId) {
		this.merchantTxnId = merchantTxnId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponce() {
		return Responce;
	}

	public void setResponce(String responce) {
		Responce = responce;
	}

	

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public String getRequest() {
		return Request;
	}

	public void setRequest(String request) {
		Request = request;
	}

}
