package com.npst.upi.portal.merchant.service;

import java.math.BigInteger;

import org.springframework.stereotype.Component;
@Component
public class CodeResponseDto {

	private int code;

	private String codetype;

	private String description;

	private byte displayorder;

	private BigInteger parentcodeid;

	private byte status;

	private String value;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodetype() {
		return codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getDisplayorder() {
		return displayorder;
	}

	public void setDisplayorder(byte displayorder) {
		this.displayorder = displayorder;
	}

	public BigInteger getParentcodeid() {
		return parentcodeid;
	}

	public void setParentcodeid(BigInteger parentcodeid) {
		this.parentcodeid = parentcodeid;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CodeResponseDto [code=" + code + ", codetype=" + codetype + ", description=" + description
				+ ", displayorder=" + displayorder + ", parentcodeid=" + parentcodeid + ", status=" + status
				+ ", value=" + value + "]";
	}
	
	
}
