/**
 * 
 */
package com.npst.upi.portal.merchant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.MerchantAuthDetailsRequest;
import com.npst.upi.portal.merchant.dto.MerchantAuthDetailsResponse;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.service.MerchantsService;

/**
 * @author Rahul Chaudhary
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class MerchantAuthDetailsController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MerchantAuthDetailsController.class);

	@Autowired
	private MerchantsService merchantsService;
	
	@RequestMapping(path={Constants.AUTH_DETAILS, Constants.AUTH_VALADD}, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMerchantInfo(@RequestBody MerchantAuthDetailsRequest merchantInfoRequest) {
		//find details from db by vpa
		log.trace("finding for validate vpa {} form DB", merchantInfoRequest.getVpa() );
		Merchants merchant = merchantsService.findByMerchantVPAAndMerchantTypeAndActive(merchantInfoRequest.getVpa(), merchantInfoRequest.getMerchantType(), Boolean.TRUE);
		
		MerchantAuthDetailsResponse merchantInfoResponse = new MerchantAuthDetailsResponse(merchantInfoRequest.getVpa());
		merchantInfoResponse.setAccountNo(merchant.getMerchantAccountNo());
		merchantInfoResponse.setIfsc(merchant.getMerchantIFSCCode());
		log.trace("Responce back from DB {} ", merchantInfoResponse );

		return new ResponseEntity<>(merchantInfoResponse, HttpStatus.OK);
	}

}
