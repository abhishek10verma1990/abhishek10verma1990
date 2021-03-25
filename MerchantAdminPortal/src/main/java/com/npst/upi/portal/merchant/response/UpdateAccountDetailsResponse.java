package com.npst.upi.portal.merchant.response;

import java.util.List;

public class UpdateAccountDetailsResponse {

	private String account_no;
	private String mobile;
	private String merchant_VPA;
	private String mccode;
	private List vpa;
	private List additionalmobile;
	private String msg;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMerchant_VPA() {
		return merchant_VPA;
	}
	public void setMerchant_VPA(String merchant_VPA) {
		this.merchant_VPA = merchant_VPA;
	}
	public String getMccode() {
		return mccode;
	}
	public void setMccode(String mccode) {
		this.mccode = mccode;
	}
	public List getVpa() {
		return vpa;
	}
	public void setVpa(List vpa) {
		this.vpa = vpa;
	}
	public List getAdditionalmobile() {
		return additionalmobile;
	}
	public void setAdditionalmobile(List additionalmobile) {
		this.additionalmobile = additionalmobile;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
