/**
 * 
 */
package com.npst.upi.portal.merchant.dto;

import java.io.Serializable;

/**
 * @author Rahul Chaudhary
 *
 */
public class MerchantOnboardingSwitchResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vpa;
	private boolean exist;
	
	public String getVpa() {
		return vpa;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}

}
