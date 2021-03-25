package com.npst.upi.portal.merchant.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.constant.MerchantRegStatus;
import com.npst.upi.portal.merchant.dto.MerchantUPIRegDto;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.response.MerchantRegistrationResponse;
import com.npst.upi.portal.merchant.service.MerchantUPISwitchRegistrationService;
import com.npst.upi.portal.merchant.utility.ApiRestClient;

@Service
public class MerchantUPISwitchRegistrationServiceImpl implements MerchantUPISwitchRegistrationService {

	private static final Logger log = LoggerFactory.getLogger(MerchantUPISwitchRegistrationServiceImpl.class);
	@Autowired
	private MerchantsRepo merchantsRepo;
	@Autowired
	private ApiRestClient httpClient;
	
	@Autowired
	private ObjectMapper mapper;
	@Value("${upi.switch.url.merchant.registration}")
	private String switchURL;

	@Override
	public void merchantUPISwitchRegistration() throws ApplicationException {
		try {
			List<Merchants> list = merchantsRepo.findByStatus(MerchantRegStatus.SUCCESS_STATE_1.getStatus());
			log.info("Merchants list from Data base : {}", list.size());
			if(list.size()==0) {
				log.info("No merchants to On-board In UPI ");
			}
			else {
			for (Merchants merchant : list) {
				MerchantUPIRegDto merchnatupidto=new MerchantUPIRegDto();
				setMerchnatupidto(merchant,merchnatupidto);
				log.info("sending request to UPI: {}", merchnatupidto);
				HttpEntity<MerchantUPIRegDto> httpEntity = new HttpEntity<>(merchnatupidto);
			    String response = httpClient.httpExchange(switchURL.trim(), HttpMethod.POST, httpEntity, String.class);
				log.info("response from UPI System : {}", response);
				MerchantRegistrationResponse regResponse = mapper.readValue(response, MerchantRegistrationResponse.class);
					if(Constants.SUCCESS_MER.equalsIgnoreCase(regResponse.getMsgId())) {
						log.info("Merchant Success from UPI with Responce Msg  {} , for VPA {} :",regResponse.getMsgId(), merchnatupidto.getVpa());
						merchantsRepo.updatemerchantStatus(MerchantRegStatus.SUCCESS_STATE_2.getStatus(),merchant.getMerchantVPA());
						}
					else {
						log.info("Merchant Failure from UPI with Responce Msg  {} , for VPA {} :",regResponse.getMsgId(), merchnatupidto.getVpa());
						merchantsRepo.updatemerchantStatus(MerchantRegStatus.FAILURE_STATE_4.getStatus(),merchant.getMerchantVPA());
					}
			}
	}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
	}
	
	private void setMerchnatupidto(Merchants merchant, MerchantUPIRegDto merchnatupidto) {
		merchnatupidto.setAadhar(merchant.getAadharNumber());
		merchnatupidto.setMcccode(merchant.getMccCode());
		merchnatupidto.setOrgName(merchant.getMerchantOrgName());
		merchnatupidto.setSettlementBank(merchant.getSettlementBank());
		merchnatupidto.setBankAccountNo(merchant.getMerchantAccountNo());
		merchnatupidto.setMobile(merchant.getContactNo());
		merchnatupidto.setVpa(merchant.getMerchantVPA());
		merchnatupidto.setApiBank(merchant.getApiBank());
		merchnatupidto.setIfscCode(merchant.getMerchantIFSCCode());
		merchnatupidto.setMerchantType(merchant.getMerchantType());
		merchnatupidto.setContactName(merchant.getMerchantOrgName());		
		}

}