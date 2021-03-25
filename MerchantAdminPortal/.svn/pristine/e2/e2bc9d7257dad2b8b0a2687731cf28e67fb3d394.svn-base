package com.npst.upi.portal.merchant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.AccountSearchRequestDto;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.MerchantRespDto;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.dto.UpdateAccountDetailsDto;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.response.ResponseWithPagination;
import com.npst.upi.portal.merchant.response.UpdateAccountDetailsResponse;
import com.npst.upi.portal.merchant.service.AccountSearchService;
import com.npst.upi.portal.merchant.utility.Utility;

@RestController
@RequestMapping(Constants.BASEURL)
public class AccountNoSearchController {
	private static final Logger log = LoggerFactory.getLogger(AccountNoSearchController.class);

	@Autowired
	AccountSearchService accountSearchService;
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	MerchantsRepo merchantsRepo;
	@RequestMapping(value =  Constants.ACCOUNT_SEARCH, method = RequestMethod.POST)
	public ResponseEntity<Object> getAccountDetails(@RequestBody String Message) {

		log.trace("Search Account No from DB");//not from DB it will come from ADM service
		
		AccountSearchRequestDto accountSearchRequestDto=null;

		log.trace("Search Account Controller");
		
		accountSearchRequestDto = Utility.getReqJSONDate(Message);
		log.info("GOT REQ FOR ACC : {} AND DP CODE : {}",accountSearchRequestDto.getAccount(),accountSearchRequestDto.getDpcd());
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
	
		Merchants accountSerachResponse=accountSearchService.getAccountDetails(Message,accountSearchRequestDto);
		if(accountSerachResponse!=null) {
			log.info("GOT DETAILS FROM KYC***");
			apiResponseDto.setResponse(accountSerachResponse);
			accountSearchService.Setparam(accountSerachResponse,accountSearchRequestDto);
		log.trace("Successfully get the Account Details {}",accountSerachResponse);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		}
		else {
			log.info("No DATA FOUND  FOR {} :",accountSearchRequestDto.getAccount());
			ApiResponseDto apiResponseDtofail = new ApiResponseDto(ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus(),
					ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus_code(), ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus_msg());
			
			return new ResponseEntity<>(apiResponseDtofail, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value =  Constants.UPDATE_ACCOUNT_DETAILS, method = RequestMethod.POST)
	public ResponseEntity<Object> updateAccountDetails(@RequestBody UpdateAccountDetailsDto requestDto) {
		ApiResponseDto apiResponseDto=null;
		log.trace("Update Account Details");
		 apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), 
				 ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		UpdateAccountDetailsResponse accountSerachResponse=accountSearchService.updateAccountDetails(requestDto);
		
		if(accountSerachResponse!=null&&
				!Constants.MERCHANTEXIST.equalsIgnoreCase(accountSerachResponse.getMsg())) {
			
			apiResponseDto.setResponse(accountSerachResponse);	
			
			log.info("Successfully get the Account Details {}",accountSerachResponse);
		}
		
		else {
			
			 apiResponseDto = new ApiResponseDto(ResponseStatus.MERCHANT_VPA_QR_IN_USED.getStatus(), 
					 ResponseStatus.MERCHANT_VPA_QR_IN_USED.getStatus_code(), ResponseStatus.MERCHANT_VPA_QR_IN_USED.getStatus_msg());
			
			 log.info("FAILED Merchant Account Details already On-boarded {}",accountSerachResponse);
		}
	
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
		
	}
//Account no search for download and generate QR
	
	@RequestMapping(value =  Constants.ACCOUNT_DETAIL_QR, method = RequestMethod.GET)
	public ResponseEntity<Object> getAccountforQrGenerate(@RequestParam String merchantAccountNo,@RequestParam String branchcode
			) {
		List<Merchants> merchant = merchantsRepo.findByMerchantAccountNoAndStatusOrBranchCodeAndStatus(merchantAccountNo,2,branchcode,2);
		MerchantRespDto dto = null;
		if (!merchant.isEmpty()) {
			dto =new MerchantRespDto(merchant);
		}else {
			log.info("No DATA FOUND  for SAS {} :",merchant);
			ApiResponseDto apiResponseDtofail = new ApiResponseDto(ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus(),
					ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus_code(), ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus_msg());
			
			return new ResponseEntity<>(apiResponseDtofail, HttpStatus.OK);
		}
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(dto);	
		log.trace("Successfully get the Account Details {}",dto);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
	
	
	@RequestMapping(value =  Constants.DOWNLOAD_DETAIL_QR, method = RequestMethod.GET)
	public ResponseEntity<Object> getAccountforQrDownload(@RequestParam String merchantAccountNo,@RequestParam String branchcode
			) {
		List<Merchants> merchant = merchantsRepo.findByMerchantAccountNoAndStatusOrBranchCodeAndStatus(merchantAccountNo,3,branchcode,3);
		MerchantRespDto dto = null;
		if (!merchant.isEmpty()) {
			dto =new MerchantRespDto(merchant);
		}else {
			log.info("No DATA FOUND  for SAS {} :",merchant);
			ApiResponseDto apiResponseDtofail = new ApiResponseDto(ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus(),
					ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus_code(), ResponseStatus.MERCHANT_ACCOUNT_DOES_NOT_EXIST.getStatus_msg());
			
			return new ResponseEntity<>(apiResponseDtofail, HttpStatus.OK);
		}
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(dto);	
		log.trace("Successfully get the Account Details {}",dto);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
	
	
	
	
}
