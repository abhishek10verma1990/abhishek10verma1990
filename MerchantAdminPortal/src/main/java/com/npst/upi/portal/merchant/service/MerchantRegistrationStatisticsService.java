package com.npst.upi.portal.merchant.service;

import java.util.List;

import com.npst.upi.portal.merchant.dto.MerchantRegistrationStatisticsRequest;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationStatisticsResponse;
import com.npst.upi.portal.merchant.exception.ApplicationException;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
public interface MerchantRegistrationStatisticsService {
	
List<MerchantRegistrationStatisticsResponse> merchantRegStatistics(MerchantRegistrationStatisticsRequest merchantBulkVpaDTO)throws ApplicationException;

}
