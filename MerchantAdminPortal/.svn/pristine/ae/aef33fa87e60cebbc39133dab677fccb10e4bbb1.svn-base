/**
 * 
 */
package com.npst.upi.portal.merchant.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.npst.upi.portal.merchant.dto.UploadedFileInfoDto;
import com.npst.upi.portal.merchant.entity.UploadedFileInfo;
import com.npst.upi.portal.merchant.exception.ApplicationException;

/**
 * @author Rahul Chaudhary
 *
 */
public interface MerchantRegistrationService {

	UploadedFileInfo registerMerchants(MultipartFile merchantsFile) throws ApplicationException;

	List<UploadedFileInfoDto> getUploadedFilesInfo();

}
