/**
 * 
 */
package com.npst.upi.portal.merchant.service.impl;

import java.util.Date;
import java.util.List;

import org.mortbay.log.Log;
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
import com.npst.upi.portal.merchant.core.BharatQrGenerator;
import com.npst.upi.portal.merchant.core.QrGeneratorUtil;
import com.npst.upi.portal.merchant.core.UpiQrCodeGenerator;
import com.npst.upi.portal.merchant.dto.MerchantUPIRegDto;
import com.npst.upi.portal.merchant.dto.QRCodeRequest;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.response.MerchantRegistrationResponse;
import com.npst.upi.portal.merchant.service.QrCodeService;
import com.npst.upi.portal.merchant.utility.ApiRestClient;
import com.npst.upi.portal.merchant.utility.ZipUtility;

/**
 * @author Rahul Chaudhary
 *
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {
	private static final Logger log = LoggerFactory.getLogger(QrCodeServiceImpl.class);

	@Autowired
	private ApiRestClient httpClient;
	
	@Autowired
	private ObjectMapper mapper;
	@Value("${upi.switch.url.merchant.registration}")
	private String switchURL;
	
	@Autowired
	private MerchantsRepo merchantsRepo;
	
	@Autowired
	private BharatQrGenerator bqrGenerator;
	
	@Autowired
	private UpiQrCodeGenerator upiqrgegenerator;
	
	@Autowired
	private QrGeneratorUtil qrutil;
	

	@Override
	public void generateQr(QRCodeRequest qrcoderequest) throws Exception {
		List<Merchants> merchantsList = merchantsRepo.findByBranchCodeAndMerchantTypeAndStatus(qrcoderequest.getBranchcode(),
				qrcoderequest.getQrtype(), MerchantRegStatus.SUCCESS_STATE_2.getStatus());
		/*List<Merchants> merchantsList = merchantsRepo.findByBranchCodeAndMerchantType(qrcoderequest.getBranchcode(),
				qrcoderequest.getQrtype());*/
		for (Merchants merchant : merchantsList) {
			if("UQR".equalsIgnoreCase("UQR")) {
				upiqrgegenerator.generateQr(merchant);
			} else if("BQR".equals(qrcoderequest.getQrtype())) {
				bqrGenerator.generateQr(merchant);
			}
			merchant.setStatus(MerchantRegStatus.SUCCESS_STATE_3.getStatus());
		}
		merchantsRepo.save(merchantsList);

	}

	@Override
	public String downloadQr(QRCodeRequest qrcoderequest) throws Exception {
		Merchants merchant=new Merchants();
		
		merchant.setBranchCode(qrcoderequest.getBranchcode());
		
		String dirPath = qrutil.generateFilePath(merchant);
		return ZipUtility.zipDirectory(dirPath, "merchantqrcode");
	}

	@Override
	public void generateQr(Merchants merchant) throws Exception {
		if("UQR".equals(merchant.getMerchantType())) {
			upiqrgegenerator.generateQr(merchant);
		} else if("BQR".equals(merchant.getMerchantType())) {
			bqrGenerator.generateQr(merchant);
		}
	}
	
	//SAS
	
	@Override
	public String generateSASQr(String qrcoderequest) throws Exception {
		String isQr = "3";
		
		List<Merchants> merchantsList = merchantsRepo.findByMerchantAccountNoandstatus(qrcoderequest,2);//need to be on vpa
	
		for (Merchants merchant : merchantsList) {
			MerchantUPIRegDto merchnatupidto=new MerchantUPIRegDto();
			 setMerchnatupidto(merchant,merchnatupidto);
			 
			/* log.info("SWITCH URL :{}",switchURL);
			 log.info("Req TO UPI SERVER {} ",merchnatupidto.toString());*/
			 
			/*HttpEntity<MerchantUPIRegDto> httpEntity = new HttpEntity<>(merchnatupidto);
			System.out.println(httpEntity.toString());
			String response = httpClient.httpExchange(switchURL.trim(), HttpMethod.POST, httpEntity, String.class);
			log.info("response from UPI System : {}", response);
			
			MerchantRegistrationResponse regResponse = mapper.readValue(response, MerchantRegistrationResponse.class);*/
			//String regResponse="hello";
		
				//for By pass use "/"
						
					if("UQR".equals("UQR")) {
						merchant.setMerchantType("UQR");
						upiqrgegenerator.generateQr(merchant);
					} else if("BQR".equals(merchant.getMerchantType())) {
						bqrGenerator.generateQr(merchant);
					}
				merchant.setStatus(MerchantRegStatus.SUCCESS_STATE_3.getStatus());
						
										
			merchantsRepo.save(merchant);
		}
		return isQr;
		

	}
	
	private void setMerchnatupidto(Merchants merchant, MerchantUPIRegDto merchnatupidto) {
		merchnatupidto.setAadhar(merchant.getAadharNumber());
		merchnatupidto.setMcccode(merchant.getMccCode());
		merchnatupidto.setOrgName(merchant.getMerchantOrgName());
		merchnatupidto.setSettlementBank(merchant.getSettlementBank());
		merchnatupidto.setBankAccountNo(merchant.getMerchantAccountNo());
		merchnatupidto.setMobile(merchant.getContactNo());
		merchnatupidto.setVpa(merchant.getMerchantVPA());
		merchnatupidto.setAddress(merchant.getMerchantAddress());
		merchnatupidto.setApiBank(merchant.getApiBank());
		merchnatupidto.setIfscCode(merchant.getMerchantIFSCCode());
		merchnatupidto.setCreatedDt(new Date());
		merchnatupidto.setMerchantType(merchant.getMerchantType());
		merchnatupidto.setCallbackURL(merchant.getMerchantCallbackURL());
		merchnatupidto.setContactName(merchant.getMerchantOrgName());
		merchnatupidto.setManagerName("ABC");
		merchnatupidto.setOperatorEmail(merchant.getOperatorEmail());
		merchnatupidto.setOperatorMobileNumber(merchant.getContactNo());
		merchnatupidto.setOrgCode(merchant.getOrgCode());
		merchnatupidto.setPanNumber(merchant.getMerchantPanNumber());
		
	}
	@Override
	public String downloadSASQr(String qrcoderequest,String branchcode) throws Exception {
		List<Merchants> merchantsList = merchantsRepo.findByMerchantAccountNoOrBranchCode(qrcoderequest,branchcode);
		
		if(merchantsList.isEmpty()) {
			return null;
		}
		String dirPath = null;
		
		
		
		for (Merchants merchant : merchantsList) {
			dirPath = qrutil.generateFilePath(merchant);
			if(qrcoderequest==null) {
				Merchants merchant1=new Merchants();
				merchant1.setBranchCode(branchcode);
				dirPath = qrutil.generateFilePath(merchant1);
				dirPath=dirPath.substring(0,dirPath.lastIndexOf("UQR"));
				break;
			}
			else {
				dirPath.substring(0,dirPath.lastIndexOf("/"));
				
			}
		}
		return ZipUtility.zipDirectory(dirPath, "merchantqrcode");
	}

}
