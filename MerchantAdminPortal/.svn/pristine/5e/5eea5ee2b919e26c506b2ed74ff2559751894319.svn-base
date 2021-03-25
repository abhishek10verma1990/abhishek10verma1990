package com.npst.upi.portal.merchant.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.dto.SimpleDto;
import com.npst.upi.portal.merchant.dto.TransactionReconcillationRequest;
import com.npst.upi.portal.merchant.exception.UPIPortalException;
import com.npst.upi.portal.merchant.response.GetPaginatedServiceResp;
import com.npst.upi.portal.merchant.response.PaginatedApiResponseDto;
import com.npst.upi.portal.merchant.service.TransactionReconciliationService;
import com.npst.upi.portal.merchant.utility.Utility;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class TransactionReconciliationController {
	private static final Logger log = LoggerFactory.getLogger(TransactionReconciliationController.class);
	private static Integer MAXL_IMIT=Constants.MAX_LIMIT;
	@Autowired
	private TransactionReconciliationService reconciliationService;

	@RequestMapping(path =Constants.TXN_RECON_REPO, method = RequestMethod.POST)
	ResponseEntity<Object> getreconciliationReport(@RequestBody TransactionReconcillationRequest request) {
		ApiResponseDto apirespinse = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			log.info("Inside getreconciliationReport{}",request.toString());
			SimpleDto dto = new SimpleDto();
			dto.setRrn(request.getRrn());
			dto.setVpa(request.getVpa());
			Utility.setPageAndSize(dto, request.getPage(), request.getSize());
			dto.setQrType(request.getMerchantType());
			dto.setStartDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(request.getFromDate())), 
                    TimeZone.getDefault().toZoneId()));
			dto.setEndDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(request.getToDate())), 
                    TimeZone.getDefault().toZoneId()));
			log.info("date request{}",dto.toString());
			GetPaginatedServiceResp<com.npst.upi.portal.merchant.dto.MerchantsTxn> out = reconciliationService.getReconciliationByDates(dto);

			if (out != null) {
				out.getPageOb().setNumberOfElements(out.getList().size());
				PaginatedApiResponseDto response = new PaginatedApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), out.isPagingSupport(), out.getList(), out.getPageOb(),
						MAXL_IMIT);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception | UPIPortalException e) {
			e.printStackTrace();
			log.error("Error : {}", e);
			apirespinse.setStatus(ResponseStatus.ERROR.getStatus());
			apirespinse.setStatusCode(ResponseStatus.ERROR.getStatus_code());
			apirespinse.setStatusMsg(ResponseStatus.ERROR.getStatus_msg());
			apirespinse.setResponse("");
		}
		return new ResponseEntity<>(
				apirespinse,
				HttpStatus.OK);

	}
}
