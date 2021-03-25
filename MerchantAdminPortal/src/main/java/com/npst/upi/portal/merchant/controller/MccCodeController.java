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
import com.npst.upi.portal.merchant.dto.MccCodeResponseDTO;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.entity.MccCode;
import com.npst.upi.portal.merchant.service.MccCodeService;

@RestController
@RequestMapping(Constants.BASEURL)
public class MccCodeController {

/*
 * @Author Sweta Pandey	
 */
	
	
private static final Logger log = LoggerFactory.getLogger(MccCodeController.class);
	
	@Autowired
	private MccCodeService mCodeService;
	
	
	@RequestMapping(value=Constants.MCC_CODES,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMCCCodes() {
		log.trace("Fetching Mcc codes from DB");
		List<MccCode> codes = mCodeService.getAllMccCodes();
		MccCodeResponseDTO mccResponse = new MccCodeResponseDTO(codes);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(mccResponse);	
		log.trace("Successfully get the Mcc Codes {}",mccResponse);

		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}
	
}
