package com.npst.upi.portal.merchant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.AuthorizerDto;
import com.npst.upi.portal.merchant.dto.MerchantStatusDto;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.service.MerchantAuth;

@RestController
@RequestMapping(Constants.BASEURL)
public class MerchantAuthorizerController {
	
    @Autowired
	MerchantAuth merchantAuth;
    
    private static final Logger log = LoggerFactory.getLogger(MerchantAuthorizerController.class);
	
	@GetMapping(Constants.Get_Auth_Data)
	public ResponseEntity getAuthorizerData(){
		log.trace("Get auth data from database");
	ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		List<AuthorizerDto> responsedata = merchantAuth.getAuthorizerData();
		
		if(!responsedata.isEmpty()) {
			apiResponseDto.setResponse(responsedata);
		}
		log.trace("Get authorizer data successfully"+apiResponseDto.toString());
		return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
	}
	
	
	@RequestMapping(value =  Constants.UPDATE_MERCHANT_STATUS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> UpdateMerchantStatus(@RequestBody MerchantStatusDto merchantStatusDto) {
		log.trace("Get request data from MerchantStatusDto"+merchantStatusDto.toString());
		List<MerchantStatusDto>  resp = merchantAuth.UpdateMerchantStatus(merchantStatusDto);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(resp);
		log.trace("Update merchant status successfully"+apiResponseDto.toString());
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
}
