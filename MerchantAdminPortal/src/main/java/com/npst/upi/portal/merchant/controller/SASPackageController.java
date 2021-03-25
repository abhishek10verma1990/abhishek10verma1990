package com.npst.upi.portal.merchant.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.CommonRequest;
import com.npst.upi.portal.merchant.dto.LoginReq;
import com.npst.upi.portal.merchant.dto.LoginRequestDto;
import com.npst.upi.portal.merchant.response.CommonResponse;
import com.npst.upi.portal.merchant.service.LoginLogService;
import com.npst.upi.portal.merchant.service.LoginRespService;

@RestController
@RequestMapping(Constants.BASEURL)
public class SASPackageController {
	private static final Logger log = LoggerFactory.getLogger(SASPackageController.class);
	@Autowired
	LoginRespService loginserviceImpl;

	@Autowired
	LoginLogService loginLogService;

	@RequestMapping(Constants.LOGIN_REQ)
	public ResponseEntity<Object> getSASDetails(@RequestBody CommonRequest<LoginReq> request) {
		log.trace("Fatching branch from DB");
		LoginRequestDto loginrequest=new LoginRequestDto();
		loginrequest.setChannel("PORTAL");
		loginrequest.setRequest(request.toString());
		loginrequest.setClientip("10.2.3.45");
		loginrequest.setCreated_on(new Date());
		loginrequest.setUrl(Constants.BASEURL.concat(Constants.LOGIN_REQ));
		loginLogService.saveLoginAudit(loginrequest, request.getRequest_data().getLogin_token());
		CommonResponse<?> loginResponse=loginserviceImpl.getLoginDetails(request);
		if(loginResponse!=null) {
			loginrequest.setModified_on(new Date());
			loginrequest.setResponse(loginResponse.toString());
			loginLogService.saveLoginAudit(loginrequest, request.getRequest_data().getLogin_token());
		}
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
}
