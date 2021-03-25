package com.npst.upi.portal.merchant.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.MerchantDetailsDTO;
import com.npst.upi.portal.merchant.dto.MerchantsAndStatusDetailsdto;
import com.npst.upi.portal.merchant.dto.MerchantsDto;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.service.MerchantsService;


/**
 * @author Sweta Pandey
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class MerchantsDetailController {

	private static final Logger log = LoggerFactory.getLogger(MerchantsDetailController.class);
	
	@Autowired
	private MerchantsService merchantService;
	
	@RequestMapping(value=Constants.MERCHANT_DETAILS,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllMerchantDetails() {
		log.trace("Fetching all merchants.....");
		//find all branch codes for a particular bank(tenant)
		List<MerchantsAndStatusDetailsdto> merchants = merchantService.loadAllCustomers();
		//MerchantDetailsDTO merDto= new MerchantDetailsDTO(merchants);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(merchants);	
		log.trace("Successfully get the Branches {}",merchants);

		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
}
