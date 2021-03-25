package com.npst.upi.portal.merchant.controller;

import java.util.*;

import java.util.List;

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
import com.npst.upi.portal.merchant.dto.VPAGenrateDto;
import com.npst.upi.portal.merchant.service.CodeResponseDto;
import com.npst.upi.portal.merchant.service.CodeType;

@RestController
@RequestMapping(Constants.BASEURL)
public class MasterCodeTypeController {
	
	@Autowired
	CodeType codeType;
	private static final Logger log = LoggerFactory.getLogger(MasterCodeTypeController.class);
    
	@RequestMapping(value =  Constants.DROP_DWON_CODE, method = RequestMethod.POST)
	public ResponseEntity<Object> getcodeType(@RequestBody VPAGenrateDto request) {
	    log.trace("Get request data from VPAGenrateDto"+request.toString());
	ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
	List<CodeResponseDto> listCode=codeType.getCodeDetails(request.getCodeType());
	
	if(!listCode.isEmpty()) {
		apiResponseDto.setResponse(listCode);
	}
	    log.trace("Response data "+apiResponseDto.toString());
	return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}	
	
	@RequestMapping(value =  Constants.VPA_GENERATE, method = RequestMethod.POST)
	public ResponseEntity<Object> generateVpa(@RequestBody VPAGenrateDto request) {
		   log.trace("Get request data from VPAGenrateDto"+request.toString());
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		String vpa=codeType.generateVpa(request);
		if(vpa!=null) {
			apiResponseDto.setResponse(vpa);
		}
		   log.trace("Response data"+apiResponseDto.toString());
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK); 
	}
	
	@RequestMapping(value =  Constants.GENERATE_SUFFIX_VPA, method = RequestMethod.POST)
	public ResponseEntity<Object> generateSuffixVpa(@RequestBody VPAGenrateDto merchantVPA) {
		log.trace("Get request data from VPAGenrateDto"+merchantVPA.toString());
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		List<String> listdata=codeType.generateSuffixVpa(merchantVPA);
		Map<String, Object> mapdata = new HashMap<>();
		mapdata.put("Vpa", listdata);
		System.out.println("VPA DATA IS  "+mapdata);
		if(!listdata.isEmpty()) {
			apiResponseDto.setResponse(mapdata);
		}
		log.trace("Response data"+apiResponseDto.toString());
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);  
	}
}
