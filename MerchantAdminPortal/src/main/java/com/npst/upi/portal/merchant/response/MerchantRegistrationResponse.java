/**
 * 
 */
package com.npst.upi.portal.merchant.response;

import java.io.Serializable;

/**
 * @author Rahul Chaudhary
 *
 */
public class MerchantRegistrationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String regId;
	private String msgId;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	

	
	
}
