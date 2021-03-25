/**
 * 
 */
package com.npst.upi.portal.merchant.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/**
 * @author npst
 *
 */
public class AuditTrailDto {

	private long id;
	private Object request;
	private Object response;
	private String operation;
	private String username;
	@JsonSerialize(using = DateSerializer.class)
	private Date modifiedOn;
	private String modulename;
	@JsonIgnore
	private String entityclass;

	public AuditTrailDto() {
		super();
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getEntityclass() {
		return entityclass;
	}

	public void setEntityclass(String entityclass) {
		this.entityclass = entityclass;
	}


	public Object getRequest() {
		return request;
	}


	public void setRequest(Object request) {
		this.request = request;
	}


	public Object getResponse() {
		return response;
	}


	public void setResponse(Object response) {
		this.response = response;
	}


	@Override
	public String toString() {
		return "AuditTrailDto [id=" + id + ", request=" + request + ", response=" + response + ", operation="
				+ operation + ", username=" + username + ", modifiedOn=" + modifiedOn + ", modulename=" + modulename
				+ ", entityclass=" + entityclass + "]";
	}
	

}
