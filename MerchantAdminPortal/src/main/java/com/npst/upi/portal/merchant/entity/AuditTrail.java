/**
 * 
 */
package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author shujat khan
 *
 */
@Entity
@Table(name="audittraillog")
public class AuditTrail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(columnDefinition="LONGTEXT")
	private String request;
	
	@Column(columnDefinition="LONGTEXT")
	private String response;
	
	private String operation;
	
	private String username;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;
	
	private String modulename;
	
	private String entityclass;
	
	public AuditTrail() {
		super();
		this.modifiedAt=new Date(System.currentTimeMillis());
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
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
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
	@Override
	public String toString() {
		return "AuditTrail [id=" + id + ", request=" + request + ", response=" + response + ", operation=" + operation
				+ ", username=" + username + ", modifiedAt=" + modifiedAt + ", modulename=" + modulename
				+ ", entityclass=" + entityclass + "]";
	}
	
	
}
