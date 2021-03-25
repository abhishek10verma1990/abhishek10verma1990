/**
 * 
 */
package com.npst.upi.portal.merchant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npst.upi.portal.merchant.entity.UploadedFileInfo;

/**
 * @author Rahul Chaudhary
 *
 */
public interface UploadedFileInfoRepo extends JpaRepository<UploadedFileInfo, Long> {

	UploadedFileInfo findFirstByFileNameAndServiceType(String originalFilename, int servicetype);

	List<UploadedFileInfo> findByServiceType(int servicetype);
	
	

}
