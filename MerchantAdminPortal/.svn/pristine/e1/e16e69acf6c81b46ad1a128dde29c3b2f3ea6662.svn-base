/**
 * 
 */
package com.npst.upi.portal.merchant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.config.RestErrorHandler;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.BranchMasterRequest;
import com.npst.upi.portal.merchant.dto.BranchMasterResponse;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.service.BranchMasterService;

/**
 * @author Rahul Chaudhary
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class BranchMasterController {
	private static final Logger log = LoggerFactory.getLogger(BranchMasterController.class);

	@Autowired
	private BranchMasterService branchMasterService;
	
	@RequestMapping(Constants.BRANCH_DETAILS)
	public ResponseEntity<Object> getBranchCodes(@RequestBody BranchMasterRequest branchMasterRequest) {
		log.trace("Fatching branch from DB");
		//find all branch codes for a particular bank(tenant)
		List<String> branches = branchMasterService.findByBankid(branchMasterRequest);
		BranchMasterResponse branchresponse = new BranchMasterResponse(branches);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(branchresponse);	
		log.trace("Successfully get the Branches {}",branchresponse);

		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}

}
