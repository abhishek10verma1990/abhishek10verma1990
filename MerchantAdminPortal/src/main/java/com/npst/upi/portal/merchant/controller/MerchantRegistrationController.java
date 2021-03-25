/**
 * 
 */
package com.npst.upi.portal.merchant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationUpdateRequest;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.dto.UploadedFileInfoDto;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.service.MerchantRegistrationService;
import com.npst.upi.portal.merchant.service.MerchantsService;

/**
 * @author Rahul Chaudhary
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class MerchantRegistrationController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MerchantRegistrationController.class);
	
	@Autowired
	private MerchantRegistrationService merchantRegistrationService;
	
	@Autowired
	private MerchantsService merchantsSErvice;
	
	@RequestMapping(path=Constants.REGISTRATION, consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto> registerMerchants(@RequestParam("file") final MultipartFile merchantsFile) {
		log.info("Registration for Merchants process Started file {}....",merchantsFile);
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			merchantRegistrationService.registerMerchants(merchantsFile);
		} catch (ApplicationException e) {
			e.printStackTrace();
			log.error("Error : {}", e);
			apiResponseDto.setStatus(ResponseStatus.ERROR.getStatus());
			apiResponseDto.setStatusCode(ResponseStatus.ERROR.getStatus_code());
			apiResponseDto.setStatusMsg(ResponseStatus.ERROR.getStatus_msg());
			apiResponseDto.setResponse("");
		}
		
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = Constants.REGDETAILS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto> get() {
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		try {
			List<UploadedFileInfoDto> responseList = merchantRegistrationService.getUploadedFilesInfo();
			apiResponseDto.setResponse(responseList);
			return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error ", e);
			apiResponseDto.setStatus(ResponseStatus.ERROR.getStatus());
			apiResponseDto.setStatusCode(ResponseStatus.ERROR.getStatus_code());
			apiResponseDto.setStatusMsg(ResponseStatus.ERROR.getStatus_msg());
			return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value =  Constants.REGUPDATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto> activeInactiveMerchantReg(@RequestBody MerchantRegistrationUpdateRequest request) {
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg(), "");
		int status = merchantsSErvice.updateMerchantReg(request);
		if(status == 0) {
			apiResponseDto.setStatus(ResponseStatus.MERCHANT_VPA_DOES_NOT_EXIST.getStatus());
			apiResponseDto.setStatusCode(ResponseStatus.MERCHANT_VPA_DOES_NOT_EXIST.getStatus_code());
			apiResponseDto.setStatusMsg(ResponseStatus.MERCHANT_VPA_DOES_NOT_EXIST.getStatus_msg());
		} else if(status == -1) {
			apiResponseDto.setStatus(ResponseStatus.ERROR.getStatus());
			apiResponseDto.setStatusCode(ResponseStatus.ERROR.getStatus_code());
			apiResponseDto.setStatusMsg(ResponseStatus.ERROR.getStatus_msg());
		}
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}

}
