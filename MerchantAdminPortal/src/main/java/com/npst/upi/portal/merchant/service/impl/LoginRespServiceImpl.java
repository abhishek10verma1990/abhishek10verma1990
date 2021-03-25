package com.npst.upi.portal.merchant.service.impl;

import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.dto.CommonRequest;
import com.npst.upi.portal.merchant.response.CommonResponse;
import com.npst.upi.portal.merchant.response.LoginResponse;
import com.npst.upi.portal.merchant.service.LoginRespService;

@Service
public class LoginRespServiceImpl implements LoginRespService {

	@Override
	public CommonResponse getLoginDetails(CommonRequest accountSearchRequestDto) {
		CommonResponse commonResponse=getLoginRespData();
		return commonResponse;
	}

	 public CommonResponse getLoginRespData() {
		 CommonResponse commonResponse=new CommonResponse();
		 LoginResponse resp = new LoginResponse();
		 resp.setBranch_name("HEAD OFFICE BENGALURU");
		 resp.setDesignation("System Admin");
		 resp.setDesignation_code("1237");
		 resp.setDpcd("0401");
		 resp.setLogin_date("14-SEP-2018 02:46 PM");
		 resp.setName("NAGARAJAN R");
		 resp.setOffice_type("HEAD OFFICE");
		 resp.setOffice_type_code("1");
		 resp.setRole("2");
		 resp.setSection("Department of Information Technology");
		 resp.setSection_code("2603");
		 resp.setStaff_no("109897");
		 resp.setWing("Dept of IT Wing");
		 resp.setWing_code("9");
		 
		 if (Integer.parseInt(resp.getDesignation_code()) >= 1101) {
			 resp.setUserFlag(Constants.AUTHORIZER);
		}else {
			resp.setUserFlag(Constants.INITIATOR);
		}
		 commonResponse.setSession_id("CHOICE201811220000000353");
		 commonResponse.setRequest_id("POS201811220000000292");
		 commonResponse.setStatus("SUCCESS");
		 commonResponse.setDigest("9704265B324BB31799356D11C241E3345E8ADF1DF591CCDEDF1141421A9ECE80");
		 commonResponse.setResponse_data(resp);
		 return commonResponse;
		 
	 }

}
