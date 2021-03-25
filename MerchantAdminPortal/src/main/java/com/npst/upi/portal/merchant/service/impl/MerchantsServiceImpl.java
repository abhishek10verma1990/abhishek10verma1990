package com.npst.upi.portal.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.npst.upi.portal.merchant.dto.MerchantRegistrationUpdateRequest;
import com.npst.upi.portal.merchant.dto.MerchantsAndStatusDetailsdto;
import com.npst.upi.portal.merchant.dto.MerchantsDto;
import com.npst.upi.portal.merchant.entity.BranchMaster;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.entity.MerchantsError;
import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.repo.BranchMasterRepo;
import com.npst.upi.portal.merchant.repo.MerchantsErrorRepo;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.response.MerchantRegistrationResponse;
import com.npst.upi.portal.merchant.service.MerchantsService;
import com.npst.upi.portal.merchant.utility.ApiRestClient;
import com.npst.upi.portal.merchant.utility.ApplicationConstants;
import com.npst.upi.portal.merchant.utility.ServiceType;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Service
@Transactional
public class MerchantsServiceImpl implements MerchantsService {
	private static final Logger log = LoggerFactory.getLogger(MerchantsServiceImpl.class);
	@Autowired
	MerchantsRepo merchantsRepo;
	
	@Autowired
	BranchMasterRepo branchmasterrepo;
	//@Autowired
	//MerchantsAndStatusDetailsdto merchantsDto;

	@Autowired
	private MerchantsErrorRepo merchantsErrorRepo;
	
	@Autowired
	private ApiRestClient httpclient;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${upi.switch.url.merchant.changestatus:http://localhost:8140/api/v1/upi/switch/Merchant/changeMerchantStatus}")
	private String switchURL;

	@Override
	public Integer insert(List<Merchants> merchantsList) {
		int successCount = 0;
		BranchMaster branchmaster;

		if (merchantsList != null) {
			for (Merchants m : merchantsList) {
				try {
					merchantsRepo.save(m);
					//add the Branch Code Into the table branch master.
					if(validate(m.getBranchCode())) {
						 branchmaster=new BranchMaster();
						 
						branchmaster.setBankid("CANB");
						branchmaster.setBranchcode(m.getBranchCode());
						branchmasterrepo.save(branchmaster);
						
					}
					
					
					successCount++;
				} catch (DataIntegrityViolationException | ConstraintViolationException e) {
					insertMerchantErrorLogs(m, "VPA data already exists in database");
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Error : {}", e);
					if (merchantsList.get(0) != null) {
						insertMerchantErrorLogs(m, "Application Exception");
					}
				}
			}
		}
		return successCount;
	}

	
	@Override
	public List<MerchantsAndStatusDetailsdto> loadAllCustomers() {
		List<Object[]> object = merchantsRepo.getById();
		List<MerchantsAndStatusDetailsdto> list = new ArrayList<>();
		
		for(Object[] obj : object) {
			MerchantsAndStatusDetailsdto merchantsDto = new MerchantsAndStatusDetailsdto();
			merchantsDto.setMccCode(obj[0]==null?null:(String)obj[0]);
			merchantsDto.setMerchantsAccountno(obj[3]==null?null:(String)obj[3]);
			merchantsDto.setMerchantsAddress(obj[5]==null?null:(String)obj[5]);
			merchantsDto.setMerchantsMobileNo(obj[6]==null?null:(String)obj[6]);
			merchantsDto.setMerchantsVpa(obj[4]==null?null:(String)obj[4]);
			merchantsDto.setMerchantsRemark(obj[7]==null?null:(String)obj[7].toString());
			merchantsDto.setStatus(obj[8]==null?null:Integer.parseInt(obj[8].toString()));
			merchantsDto.setCustomerId(obj[1]==null?null:(String)obj[1].toString());
			merchantsDto.setMerchantsDelivery(obj[10]==null?null:(String)obj[10].toString());
			merchantsDto.setAdditionalMobileNo(obj[11]==null?null:(String)obj[11].toString());
			merchantsDto.setUserFlag(obj[9]==null?null:Integer.parseInt(obj[9].toString()));
			merchantsDto.setStatusvalue(obj[12]==null?null:(String)obj[12].toString());			
			merchantsDto.setMerchantsName(obj[2]==null?null:(String)obj[2].toString());
			
			list.add(merchantsDto);
		}
		return list;
	}

	@Override
	public void insertMerchantErrorLogs(Merchants merchant, String errorCode) {
		try {
			MerchantsError merchanterror = new MerchantsError();
			merchanterror.setMerchantOrgName(merchant.getMerchantOrgName());
			merchanterror.setMerchantVPA(merchant.getMerchantVPA());
			merchanterror.setMerchantMobileNo(merchant.getMerchantMobileNo());
			merchanterror.setMerchantAccountNo(merchant.getMerchantAccountNo());
			merchanterror.setMerchantAddress(merchant.getMerchantAddress());
			merchanterror.setMerchantIFSCCode(merchant.getMerchantIFSCCode());
			merchanterror.setCreatedDate(merchant.getCreatedDate());
			merchanterror.setManagerName(merchant.getManagerName());
			merchanterror.setMerchantCallbackURL(merchant.getMerchantCallbackURL());
			merchanterror.setMerchantAddress(merchant.getMerchantAddress());
			merchanterror.setMccCode(merchant.getMccCode());
			merchanterror.setMerchantPanNumber(merchant.getMerchantPanNumber());
			merchanterror.setOrgCode(merchant.getOrgCode());
			merchanterror.setSettlementBank(merchant.getSettlementBank());
			merchanterror.setApiBank(merchant.getApiBank());
			merchanterror.setOperatorContactName(merchant.getOperatorContactName());
			merchanterror.setOperatorMobileNumber(merchant.getOperatorMobileNumber());
			merchanterror.setOperatorEmail(merchant.getOperatorEmail());
			merchanterror.setAadharNumber(merchant.getAadharNumber());
			merchanterror.setErrorCode(errorCode);
			merchanterror.setFileName(merchant.getFileName());
			merchanterror.setBranchCode(merchant.getBranchCode());
			merchanterror.setServiceType(ServiceType.MERCHANT_REGISTRATION.getType());
			merchantsErrorRepo.save(merchanterror);
		} catch (Exception e) {
			log.info("Something went worng on saving data into Merchant table {} ", e);
		}
		
	}

	@Override
	public boolean insert(Merchants merchant) throws ApplicationException {
		BranchMaster branchmaster;
		if (merchant != null) {
			try {
				merchantsRepo.save(merchant);
				
				/*if(validate(merchant.getBranchCode())) {
					 branchmaster=new BranchMaster();
					 
					branchmaster.setBankid("CANB");
					branchmaster.setBranchcode(merchant.getBranchCode());
					branchmasterrepo.save(branchmaster);
					
				}*/
				
				return true;
			} catch (DataIntegrityViolationException | ConstraintViolationException e) {
				e.printStackTrace();
				log.error("Error : {}", e);
				insertMerchantErrorLogs(merchant, "VPA data already exists in database");
				throw new ApplicationException(ApplicationConstants.MERCHANTVPA_ALREADY_EXISTS.getErrorCode(),
						ApplicationConstants.MERCHANTVPA_ALREADY_EXISTS.getErrorDetails());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error : {}", e);
				insertMerchantErrorLogs(merchant, "Application Exception");
				throw new ApplicationException(ApplicationConstants.APPLICATION_EXCEPTION.getErrorCode(),
						ApplicationConstants.APPLICATION_EXCEPTION.getErrorDetails());
			}
		}
		return false;
	}

	private boolean validate(String branchCode) {
		
		List<BranchMaster> branch=branchmasterrepo.findByBranchcode(branchCode);
		if(branch.size()>0) {
			return false;
		}
		return true;
	}

	@Override
	public Merchants findByMerchantVPAAndMerchantType(String vpa, String merchantType) {
		return merchantsRepo.findByMerchantVPAAndMerchantType(vpa, merchantType);
	}

	@Override
	public int updateMerchantReg(MerchantRegistrationUpdateRequest request) {
		try {
			Merchants merchant = merchantsRepo.findByMerchantVPAAndMerchantType(request.getVpa(), request.getMerchantType());
			if(merchant == null)
				return 0;
			HttpEntity<Merchants> httpEntity = new HttpEntity<>(merchant);
			String response = httpclient.httpExchange(switchURL, HttpMethod.POST, httpEntity, String.class);
			MerchantRegistrationResponse merchantregresponse = mapper.readValue(response, MerchantRegistrationResponse.class);
			if(merchantregresponse != null && !StringUtils.isEmpty(merchantregresponse.getRegId())) {
				merchant.setActive(Boolean.valueOf(request.getActive()));
			}
		} catch(Exception e) {
			return -1;
		}
		return 1;
	}

	@Override
	public Merchants findByMerchantVPAAndMerchantTypeAndActive(String vpa, String merchantType, Boolean active) {
		return merchantsRepo.findByMerchantVPAAndMerchantTypeAndActive(vpa, merchantType, active);
	}
	
	/*public Merchants findBymerchantAccountNo(String accountnum){
		return merchantsRepo.findByMerchantAccountNo(accountnum);
		
	}*/
}