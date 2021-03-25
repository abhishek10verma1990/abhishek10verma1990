/**
 * 
 */
package com.npst.upi.portal.merchant.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.npst.upi.portal.merchant.constant.MerchantRegStatus;
import com.npst.upi.portal.merchant.core.PersistMerchantRegistrationDataI;
import com.npst.upi.portal.merchant.dto.UploadedFileInfoDto;
import com.npst.upi.portal.merchant.entity.UploadedFileInfo;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.repo.UploadedFileInfoRepo;
import com.npst.upi.portal.merchant.service.MerchantRegistrationService;
import com.npst.upi.portal.merchant.utility.ApplicationConstants;
import com.npst.upi.portal.merchant.utility.ServiceType;

/**
 * @author Rahul Chaudhary
 *
 */
@Service
@Transactional
public class MerchantRegistrationServiceImpl implements MerchantRegistrationService {

	private static final Logger log = LoggerFactory.getLogger(MerchantRegistrationServiceImpl.class);
	@Autowired
	private UploadedFileInfoRepo uploadedFileInfoRepo;

	private static final String DIRECTORY_NAME = "merchantregupload";

	@Autowired
	private PersistMerchantRegistrationDataI persistMerchantRegistrationData;

	@Override
	public UploadedFileInfo registerMerchants(MultipartFile merchantsFile) throws ApplicationException {
		log.info("Enter  MerchantRegistrationService for process Started file {}....",merchantsFile);
		UploadedFileInfo uploadedfileInfo = null;
		try {
			File directory = new File(DIRECTORY_NAME);
			if (!directory.exists()) {
				directory.mkdir();
			log.info("Directory Created {}", DIRECTORY_NAME);
			}
			uploadedfileInfo = uploadedFileInfoRepo.findFirstByFileNameAndServiceType(merchantsFile.getOriginalFilename(), ServiceType.MERCHANT_REGISTRATION.getType());
			log.info("Upload file info data {}....",uploadedfileInfo);
			if (uploadedfileInfo == null|| MerchantRegStatus.FILE_SUCCESSFULLY_PROCESSED.getStatus() != uploadedfileInfo.getStatus()) {
				uploadedfileInfo = new UploadedFileInfo();
				uploadedfileInfo.setContentType(merchantsFile.getContentType());
				uploadedfileInfo.setFileLocation(DIRECTORY_NAME + File.separator + merchantsFile.getOriginalFilename());
				uploadedfileInfo.setFileName(merchantsFile.getOriginalFilename());
				uploadedfileInfo.setStatus(MerchantRegStatus.NEW_FILE_UPLOADED.getStatus());
				uploadedfileInfo.setRemarks(MerchantRegStatus.NEW_FILE_UPLOADED.getDescription());
				uploadedfileInfo.setCreatedAt(LocalDateTime.now());
				uploadedfileInfo.setServiceType(ServiceType.MERCHANT_REGISTRATION.getType());
				String sizeStr = geFileSizeStr(merchantsFile.getSize());
				uploadedfileInfo.setFileSize(sizeStr);
				Path path = Paths.get(uploadedfileInfo.getFileLocation());
				Files.write(path, merchantsFile.getBytes());
				persistMerchantRegistrationData.persist(merchantsFile.getBytes(), uploadedfileInfo);
				uploadedFileInfoRepo.save(uploadedfileInfo);
			}
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			log.info("Some went worng for registerMerchants  to duplicate file creation{}....",e);
			throw new ApplicationException(ApplicationConstants.DUPLICATE_FILE_PATTERN.getErrorCode(),ApplicationConstants.DUPLICATE_FILE_PATTERN.getErrorDetails());
		} catch (Exception e) {
			log.info("Some went worng for registerMerchants {}....",e);
			uploadedfileInfo = new UploadedFileInfo();
			uploadedfileInfo.setStatus(MerchantRegStatus.FILE_FAILURE.getStatus());
			uploadedfileInfo.setRemarks(MerchantRegStatus.FILE_FAILURE.getDescription());
			uploadedFileInfoRepo.save(uploadedfileInfo);
			throw new ApplicationException(ApplicationConstants.APPLICATION_EXCEPTION.getErrorCode(),ApplicationConstants.APPLICATION_EXCEPTION.getErrorDetails());
		}
		return uploadedfileInfo;
	}

	private String geFileSizeStr(long size) {
		String sizeStr = "";
		if (size < 1024) {
			sizeStr = size + " bytes";
		} else if (size < 1024000) {
			sizeStr = (size / 1024) + " kb";
		} else {
			sizeStr = (size / (1024 * 1024)) + " mb";
		}
		return sizeStr;
	}

	@Override
	public List<UploadedFileInfoDto> getUploadedFilesInfo() {
		log.trace("uploading files...");
		List<UploadedFileInfoDto> outList = new ArrayList<>();
		List<UploadedFileInfo> resultList = uploadedFileInfoRepo
				.findByServiceType(ServiceType.MERCHANT_REGISTRATION.getType());
		if (!resultList.isEmpty()) {
			for (UploadedFileInfo fileInfo : resultList) {
				UploadedFileInfoDto out = new UploadedFileInfoDto();
				out.setRemarks(fileInfo.getRemarks());
				out.setTotalRecord(fileInfo.getTotalCount());
				out.setFailureRecord(fileInfo.getFailureCount());
				out.setSuccessRecord(fileInfo.getSuccessCount());
				out.setCreatedtime(fileInfo.getCreatedAt() + "");
				out.setFilename(fileInfo.getFileName());
				out.setId(fileInfo.getId());
				out.setStatus(fileInfo.getStatus());
				outList.add(out);
			}
		}
		return outList;
	}

}
