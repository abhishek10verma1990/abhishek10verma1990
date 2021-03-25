package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="loginlog")
@NamedQuery(name="Loginlog.findAll", query="SELECT l FROM Loginlog l")
public class Loginlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long loginlogid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;
	
	@Column(length=32)
	private String username;

	@Column(length=128)
	private String clientip;

	@Column(length=400)
	private String url;
	
	@Column(nullable=false)
	private byte status;

	@Column(length=4000)
	private String request;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_on;
	
	@Column(length=40)
	private String channel;
	
	@Column(length=4000)
	private String response;
	
	@Column(length=400)
	private String loginToken;
	
	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public Loginlog() {
	}

	public Long getLoginlogid() {
		return this.loginlogid;
	}

	public void setLoginlogid(Long loginlogid) {
		this.loginlogid = loginlogid;
	}


	public String getClientip() {
		return this.clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}