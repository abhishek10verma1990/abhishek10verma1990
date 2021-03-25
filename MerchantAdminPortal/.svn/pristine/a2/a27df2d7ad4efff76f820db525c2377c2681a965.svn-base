package com.npst.upi.portal.merchant.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.ApiResponseDto;
import com.npst.upi.portal.merchant.dto.CommonRequest;
import com.npst.upi.portal.merchant.dto.LoginReq;
import com.npst.upi.portal.merchant.dto.LoginRequestDto;
import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.dto.SASInfoResp;
import com.npst.upi.portal.merchant.dto.SASResp;
import com.npst.upi.portal.merchant.entity.SASInfo;
import com.npst.upi.portal.merchant.repo.SASInfoRepo;
import com.npst.upi.portal.merchant.response.CommonResponse;
import com.npst.upi.portal.merchant.service.LoginLogService;
import com.npst.upi.portal.merchant.service.LoginRespService;
import com.npst.upi.portal.merchant.utility.EncAESGSM;
import com.npst.upi.portal.merchant.utility.enc;

@Controller
@RequestMapping(Constants.BASEURL)
public class SASController {

	
	private static final Logger log = LoggerFactory.getLogger(SASController.class);
	@Autowired
	LoginRespService loginserviceImpl;
	
	@Autowired
	LoginLogService loginLogService;
	
	
	@Value("${GUI_URL}")
	private String GUIURL;
	
	@Autowired
	SASInfoRepo sasInfoRepo;
	
	
	
	@RequestMapping(value =  Constants.SASLOGIN)
	public ModelAndView sasLogin(@RequestBody String Message, Model model) {
		log.info("Inside SAS Login Controller");
		log.info("Req FROM SAS {}",Message);
	    final String secretKeys = "cb_B@leNQ@)!(ApI";
	    final String keyIV = "CB_$@Lt_K(y_2019";
		log.info("Final request : ");
		Map<String, String> map=enc.loginRequestParsing(Message);
		//System.out.println("Get all data "+map.get("VENDOR_ID")+""+map.get("RESPONSE_ENC")+""+map.get("RESPONSE_DIGEST")+""+map.get("RESPONSE_DATE_TIME")+""+map.get("METHOD_ID"));
		String decriptedvalue = null;
		try {
			decriptedvalue=EncAESGSM.decryptVI(map.get("RESPONSE_ENC").trim(), 
					secretKeys.getBytes(), keyIV.getBytes());
		} catch (Exception e) {
			return new ModelAndView(GUIURL.trim(), "sasUserInfo", model);
		}
		String desdata = decriptedvalue;
		String [] temp=decriptedvalue.trim().split("~");
		String stNo=temp[0];
		log.info("DATA DECRYPTED {} ", decriptedvalue);
		//SASResp KYCResp = new Gson().fromJson(Message, SASResp.class);	
		SASResp kYCResp = new SASResp();
		kYCResp.setVENDOR_ID(map.get("VENDOR_ID"));
		kYCResp.setRESPONSE_ENC(decriptedvalue);
		kYCResp.setRESPONSE_DIGEST(map.get("RESPONSE_DIGEST"));
		kYCResp.setRESPONSE_CODE("SUCCESS");
		kYCResp.setMETHOD_ID(map.get("METHOD_ID"));
		kYCResp.setRESPONSE_DATE_TIME(map.get("RESPONSE_DATE_TIME"));
		log.info("befor going to save data");
		try {
			loginLogService.saveSASLoginAudit(decriptedvalue, temp[0]);
			loginLogService.sasLogInHistory(decriptedvalue, temp[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.debug("after save record");
		log.info("RESP IN JSON {} ",kYCResp.toString());
		System.out.println("RESP IN JSON"+kYCResp.toString());
		model.addAttribute("sasUserInfo", kYCResp.toString());	
		System.out.println("Retrun data from controller"+ model);
		return new ModelAndView(GUIURL.trim()+stNo, "sasUserInfo", model);
		//return new ModelAndView("redirect:http://localhost:8080/Merchant-0.0.1/#/merchant?stNo="+stNo, "sasUserInfo", model);
		//http://localhost:8080/Merchant-0.0.1/#/merchant
		//return "merchant";
	}
	
	@RequestMapping(Constants.SAS_GET)
	public ResponseEntity<Object> getSASDetails(@RequestParam String stNo) {
		log.trace("Fatchingdata from KYC CBS Request");
		System.out.println("STNo Is {}"+stNo);
		SASInfo fetchInfo = sasInfoRepo.findByStNo(stNo);
		SASInfoResp sasInfo=null;
		if (null != fetchInfo) {
			sasInfo = new SASInfoResp();
			sasInfo.setDescCode(fetchInfo.getDescCode());
			sasInfo.setDpcd(fetchInfo.getDpcd());
			sasInfo.setStNo(fetchInfo.getStNo());
			sasInfo.setUsername(fetchInfo.getUsername());
			if (Integer.parseInt(fetchInfo.getDescCode()) >= 1101) {
				sasInfo.setUserFlag(Constants.AUTHORIZER);
			}else {
				sasInfo.setUserFlag(Constants.INITIATOR);
			}
		}else {
			log.info("No DATA FOUND  for SAS {} :",fetchInfo);
			ApiResponseDto apiResponseDtofail = new ApiResponseDto(ResponseStatus.MERCHANT_SAS_DOES_NOT_EXIST.getStatus(),
					ResponseStatus.MERCHANT_SAS_DOES_NOT_EXIST.getStatus_code(), ResponseStatus.MERCHANT_SAS_DOES_NOT_EXIST.getStatus_msg());
			
			return new ResponseEntity<>(apiResponseDtofail, HttpStatus.OK);
		}
		ApiResponseDto apiResponseDto = new ApiResponseDto(ResponseStatus.SUCCESS.getStatus(), ResponseStatus.SUCCESS.getStatus_code(), ResponseStatus.SUCCESS.getStatus_msg());
		apiResponseDto.setResponse(sasInfo);	
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
}	