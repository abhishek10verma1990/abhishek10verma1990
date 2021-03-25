/**
 * 
 */
package com.npst.upi.portal.merchant.dto;

import java.io.Serializable;

/**
 * @author Rahul Chaudhary
 *
 */
public class MerchantRegistrationUpdateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vpa;
	private String merchantType;
	private String active;
	
	public String getVpa() {
		return vpa;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

}
