package com.npst.upi.portal.merchant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationStatisticsRequest;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationStatisticsResponse;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.service.MerchantRegistrationStatisticsService;

/**
 * 
 * @author Rahul Chaudhary
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class MerchantRegistrationStatisticsController {

	private final static Logger log = LoggerFactory.getLogger(MerchantRegistrationStatisticsController.class);

	@Autowired
	MerchantRegistrationStatisticsService vpaBulkDownloadService;

	@Value("${merchantbulkvpa.filetype.success}")
	private String successFileType;

	@Value("${merchantbulkvpa.filetype.failure}")
	private String failureFileType;



	@RequestMapping(value = "/registration/statistics/details", method = RequestMethod.POST)
	public ResponseEntity<Object> merchantBulkVpa(
			@RequestBody final MerchantRegistrationStatisticsRequest merchantBulkVpaDTO) {
		
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(),
				ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");

		try {

			List<MerchantRegistrationStatisticsResponse> response = vpaBulkDownloadService.merchantRegStatistics(merchantBulkVpaDTO);

			if (null != response && !response.isEmpty()) {
				apiResponseDto.setResponse(response);
			}

		} catch (ApplicationException e) {
			log.info("Some exception occurred: {}", e);
			apiResponseDto.setStatus(ResponseStatus.ERROR.getStatus());
			apiResponseDto.setStatusCode(ResponseStatus.ERROR.getStatus_code());
			apiResponseDto.setStatusMsg(ResponseStatus.ERROR.getStatus_msg());
			apiResponseDto.setResponse("");
		}
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);

	}

	
}
