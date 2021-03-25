/**
 * 
 */
package com.npst.upi.portal.merchant.config;

/**
 * @author Rahul Chaudhary
 *
 */
public enum TenantIdNames {

	CANB("CANB"), COSMOS("COSMOS");
	
	private final String name;
	
	TenantIdNames(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	

}
