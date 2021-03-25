package com.npst.upi.portal.merchant.dto;

import java.io.Serializable;
import java.util.Date;

public class LoginRequestDto implements Serializable {

	private static final long serialVersionUID = -650280138234207306L;
	private String request;
	private String response;
	private String url;
	private String username;
	private String clientip;
	private byte status;
	private Date modified_on;
	private Date created_on;
	private String channel;
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClientip() {
		return clientip;
	}
	public void setClientip(String clientip) {
		this.clientip = clientip;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Override
	public String toString() {
		return "LoginRequestDto [request=" + request + ", response=" + response + ", url=" + url + ", username="
				+ username + ", clientip=" + clientip + ", status=" + status + ", modified_on=" + modified_on
				+ ", created_on=" + created_on + "]";
	}
	
	
	
	
	
}
