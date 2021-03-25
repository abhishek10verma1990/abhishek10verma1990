package com.npst.upi.portal.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.constant.MerchantRegStatus;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationStatisticsRequest;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationStatisticsResponse;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.entity.MerchantsError;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.repo.MerchantsErrorRepo;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.service.MerchantRegistrationStatisticsService;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Service
public class MerchantRegistrationStatisticsServiceImpl implements MerchantRegistrationStatisticsService {

	private static final Logger log = LoggerFactory.getLogger(MerchantRegistrationStatisticsServiceImpl.class);
	@Autowired
	MerchantsRepo merchantsRepo;

	@Autowired
	MerchantsErrorRepo merchantsErrorRepo;

	@Override
	public List<MerchantRegistrationStatisticsResponse> merchantRegStatistics(MerchantRegistrationStatisticsRequest merchantRegStatisticsRequest)
			throws ApplicationException {

		List<MerchantRegistrationStatisticsResponse> merchantRegStatisticsList = new ArrayList<>();
		try {
			
			
			if (String.valueOf(MerchantRegStatus.FAILED.getStatus()).equals(merchantRegStatisticsRequest.getFileType())) {
				MerchantRegistrationStatisticsResponse failureRegistrationStatistics = null;
				List<MerchantsError> failedRegistrations = merchantsErrorRepo
						.findByFileName(merchantRegStatisticsRequest.getFileName());
				for (MerchantsError dublicateVpaBulkUpload : failedRegistrations) {
					failureRegistrationStatistics = new MerchantRegistrationStatisticsResponse();
					failureRegistrationStatistics.setMerchantOrgName(dublicateVpaBulkUpload.getMerchantOrgName());
					failureRegistrationStatistics.setMerchantVPA(dublicateVpaBulkUpload.getMerchantVPA());
					failureRegistrationStatistics.setMerchantMobileNo(dublicateVpaBulkUpload.getMerchantMobileNo());
					failureRegistrationStatistics.setMerchantAccountNo(dublicateVpaBulkUpload.getMerchantAccountNo());
					failureRegistrationStatistics.setMerchantIFSCCode(dublicateVpaBulkUpload.getMerchantIFSCCode());
					failureRegistrationStatistics.setCreatedDate(dublicateVpaBulkUpload.getCreatedDate());
					failureRegistrationStatistics.setManagerName(dublicateVpaBulkUpload.getManagerName());
					failureRegistrationStatistics.setMerchantCallbackURL(dublicateVpaBulkUpload.getMerchantCallbackURL());
					failureRegistrationStatistics.setMerchantAddress(dublicateVpaBulkUpload.getMerchantAddress());
					failureRegistrationStatistics.setMccCode(dublicateVpaBulkUpload.getMccCode());
					failureRegistrationStatistics.setMerchantPanNumber(dublicateVpaBulkUpload.getMerchantPanNumber());
					failureRegistrationStatistics.setOrgCode(dublicateVpaBulkUpload.getOrgCode());
					failureRegistrationStatistics.setSettlementBank(dublicateVpaBulkUpload.getSettlementBank());
					failureRegistrationStatistics.setApiBank(dublicateVpaBulkUpload.getApiBank());
					failureRegistrationStatistics.setOperatorContactName(dublicateVpaBulkUpload.getOperatorContactName());
					failureRegistrationStatistics.setOperatorMobileNumber(dublicateVpaBulkUpload.getOperatorMobileNumber());
					failureRegistrationStatistics.setOperatorEmail(dublicateVpaBulkUpload.getOperatorEmail());
					failureRegistrationStatistics.setAadharNumber(dublicateVpaBulkUpload.getAadharNumber());
					failureRegistrationStatistics.setErrorCode(dublicateVpaBulkUpload.getErrorCode());
                    failureRegistrationStatistics.setBranchCode(dublicateVpaBulkUpload.getBranchCode());
					merchantRegStatisticsList.add(failureRegistrationStatistics);
				}
				return merchantRegStatisticsList;
			}
			else {
				MerchantRegistrationStatisticsResponse successRegistrationStatistics = null;

				List<Merchants> successRegistrations = merchantsRepo
						.findByFileNameAndStatus(merchantRegStatisticsRequest.getFileName(), Integer.valueOf(merchantRegStatisticsRequest.getFileType()));
					for (Merchants vpaBulkUpload : successRegistrations) {
						successRegistrationStatistics = new MerchantRegistrationStatisticsResponse();
						successRegistrationStatistics.setMerchantOrgName(vpaBulkUpload.getMerchantOrgName());
						successRegistrationStatistics.setMerchantVPA(vpaBulkUpload.getMerchantVPA());
						successRegistrationStatistics.setMerchantMobileNo(vpaBulkUpload.getMerchantMobileNo());
						successRegistrationStatistics.setMerchantAccountNo(vpaBulkUpload.getMerchantAccountNo());
						successRegistrationStatistics.setMerchantIFSCCode(vpaBulkUpload.getMerchantIFSCCode());
						successRegistrationStatistics.setCreatedDate(vpaBulkUpload.getCreatedDate());
						successRegistrationStatistics.setManagerName(vpaBulkUpload.getManagerName());
						successRegistrationStatistics.setMerchantCallbackURL(vpaBulkUpload.getMerchantCallbackURL());
						successRegistrationStatistics.setMerchantAddress(vpaBulkUpload.getMerchantAddress());
						successRegistrationStatistics.setMccCode(vpaBulkUpload.getMccCode());
						successRegistrationStatistics.setMerchantPanNumber(vpaBulkUpload.getMerchantPanNumber());
						successRegistrationStatistics.setOrgCode(vpaBulkUpload.getOrgCode());
						successRegistrationStatistics.setSettlementBank(vpaBulkUpload.getSettlementBank());
						successRegistrationStatistics.setApiBank(vpaBulkUpload.getApiBank());
						successRegistrationStatistics.setOperatorContactName(vpaBulkUpload.getOperatorContactName());
						successRegistrationStatistics.setOperatorMobileNumber(vpaBulkUpload.getOperatorMobileNumber());
						successRegistrationStatistics.setOperatorEmail(vpaBulkUpload.getOperatorEmail());
						successRegistrationStatistics.setAadharNumber(vpaBulkUpload.getAadharNumber());
						successRegistrationStatistics.setBranchCode(vpaBulkUpload.getBranchCode());
						merchantRegStatisticsList.add(successRegistrationStatistics);
						successRegistrationStatistics = null;
					}
				} 
				return merchantRegStatisticsList;
	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
		return merchantRegStatisticsList;
	}

}
