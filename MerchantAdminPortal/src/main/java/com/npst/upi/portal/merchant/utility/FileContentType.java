/**
 * 
 */
package com.npst.upi.portal.merchant.utility;

/**
 * @author Rahul Chaudhary
 *
 */
public enum FileContentType {

	CSV("text/csv"), EXCEL("application/vnd.ms-excel|application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	
	private final String type;
	
	FileContentType(final String type) {
		this.type=type;
	}

	public String getType() {
		return type;
	}
}
