/**
 * 
 */
package com.npst.upi.portal.merchant.core;

import com.npst.upi.portal.merchant.entity.UploadedFileInfo;

/**
 * @author Rahul Chaudhary
 *
 */
public interface PersistMerchantRegistrationDataI {

	public void persist(byte[] data, UploadedFileInfo fileinfo);
	
	public String fileType();
}
