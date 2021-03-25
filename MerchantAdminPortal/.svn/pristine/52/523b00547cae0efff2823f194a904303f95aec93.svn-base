package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SASINFO")
public class SASInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(length=32)
	private String created_on;
	
	@Column(length=32)
	private String username;

	@Column(length=128)
	private String stNo;
	
	@Column(length=4000)
	private String request;
	
	@Column(length=4000)
	private String response;
	
	@Column(length=400)
	private String loginToken;
	
	@Column(name="DESG_CD",length=400)
	private String descCode;
	
	@Column(name="DPCD",length=50)
	private String dpcd;
	
	@Column(name="Login_Date",length=400)
	private String loginDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStNo() {
		return stNo;
	}

	public void setStNo(String stNo) {
		this.stNo = stNo;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getDescCode() {
		return descCode;
	}

	public void setDescCode(String descCode) {
		this.descCode = descCode;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getDpcd() {
		return dpcd;
	}

	public void setDpcd(String dpcd) {
		this.dpcd = dpcd;
	}
	
	
}