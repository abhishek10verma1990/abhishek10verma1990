package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * The persistent class for the code database table.
 * 
 */
@Entity
@Table(name = "code")
public class Code implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codeid;

	
	private int code;

	private String codetype;

	private String description;

	private byte displayorder;

	private BigInteger parentcodeid;

	private byte status;

	private String value;

	public Code() {
	}

	public Long getCodeid() {
		return this.codeid;
	}

	public void setCodeid(Long codeid) {
		this.codeid = codeid;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodetype() {
		return this.codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getDisplayorder() {
		return this.displayorder;
	}

	public void setDisplayorder(byte displayorder) {
		this.displayorder = displayorder;
	}

	public BigInteger getParentcodeid() {
		return this.parentcodeid;
	}

	public void setParentcodeid(BigInteger parentcodeid) {
		this.parentcodeid = parentcodeid;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}