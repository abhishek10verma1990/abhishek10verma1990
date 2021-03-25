package com.npst.upi.portal.merchant.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.npst.upi.portal.merchant.constant.Constants;
import com.npst.upi.portal.merchant.constant.MerchantRegStatus;
import com.npst.upi.portal.merchant.dto.AccountSearchRequestDto;
import com.npst.upi.portal.merchant.dto.MerchantKYC_Details;
import com.npst.upi.portal.merchant.dto.SASReq;
import com.npst.upi.portal.merchant.dto.SASResp;
import com.npst.upi.portal.merchant.dto.UpdateAccountDetailsDto;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.response.UpdateAccountDetailsResponse;
import com.npst.upi.portal.merchant.service.AccountSearchService;
import com.npst.upi.portal.merchant.service.AuditTrailService;
import com.npst.upi.portal.merchant.utility.ApiRestClient;
import com.npst.upi.portal.merchant.utility.EncAESGSM;
import com.npst.upi.portal.merchant.utility.Utility;


@Service
public class AccountSearchServiceImpl implements AccountSearchService {
	private static final Logger log = LoggerFactory.getLogger(AccountSearchServiceImpl.class);
		public static final int AES_KEY_SIZE = 256;
		public static final int GCM_IV_LENGTH = 12;
		public static final int GCM_TAG_LENGTH = 16;
		public static final String key = "CB_h@$H_k(y_2019";
	    public static final String secretKeys = "cb_B@leNQ@)!(ApI";
	    public static final String keyIV = "CB_$@Lt_K(y_2019";
	    String encryptedString=null;
	
	@Autowired
	MerchantsRepo merchantsRepo;
	

	@Value("${url_KYC}")
	private String KYCURL;
	
	@Autowired
	private ApiRestClient httpClient;
	
	@Autowired
	AuditTrailService auditServie;	
	
	@SuppressWarnings("rawtypes")

	private static String getHash(String secret, String message) {
		 
		 try {
			 Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
		        sha256_HMAC.init(secret_key);

		        return new String(Hex.encodeHex(sha256_HMAC.doFinal(message.getBytes("UTF-8"))));
		 }
		    catch (Exception e){
		     System.out.println("Error");
		    }
		return null;
	}
		
	private  String getEncripted() {
		String username=Utility.getProperty("KYC_USERNAME");
		String password=Utility.getProperty("KYC_PASSWORD");
		String finalString=username+":"+password;
		String BasicBase64format= Base64.getEncoder().encodeToString(finalString.getBytes());
		return BasicBase64format;
	}

	@Override
	public UpdateAccountDetailsResponse updateAccountDetails(UpdateAccountDetailsDto requestDto) {
	
		UpdateAccountDetailsResponse pdateAccountDetailsResponse= null;
		if(vpaAlradyregistor(requestDto)) {
			
			
			pdateAccountDetailsResponse = new UpdateAccountDetailsResponse(); 
			pdateAccountDetailsResponse.setAccount_no(requestDto.getMerchantAccountNo());
			pdateAccountDetailsResponse.setMsg(Constants.MERCHANTEXIST);
			log.info("vpa Alrady Exist In merchant DB {}:  ",requestDto.getMerchantVPA());
			return pdateAccountDetailsResponse;
		}
		Merchants merchant=new Merchants();
		merchant.setMerchantVPA(requestDto.getMerchantVPA());
		merchant.setManagerName(requestDto.getManagerName());
		merchant.setMerchantMobileNo(requestDto.getMerchantMobileNo());
		
		if(null==requestDto.getMerchantOrgName()) {
			merchant.setMerchantOrgName(requestDto.getManagerName());
		}
		else
		{
			merchant.setMerchantOrgName(requestDto.getMerchantOrgName());
		}
		merchant.setApiBank(requestDto.getApiBank());
		merchant.setMerchantAddress(requestDto.getMerchantAddress());
		merchant.setAdditionalMobileNo(requestDto.getAdditionalMobileNo());
		//merchant.setAdditionalBhimQr(requestDto.getAdditionalBhimQr());
		merchant.setOperatorContactName(requestDto.getOperatorContactName());
		merchant.setOperatorEmail(requestDto.getOperatorEmail());
		merchant.setOperatorMobileNumber(requestDto.getMerchantMobileNo());
		merchant.setMccCode(requestDto.getMccCode());
		
		if(null!=requestDto.getMerchantPanNumber()) {
			merchant.setMerchantPanNumber(requestDto.getMerchantPanNumber());
		}
		else {
			merchant.setMerchantPanNumber("DUMMY2343F");
		}
		if(null==requestDto.getMerchantIFSCCode()) {
			merchant.setMerchantIFSCCode("CNRB0000001");
		}
		else {
			merchant.setMerchantIFSCCode(requestDto.getMerchantIFSCCode());
		}
		merchant.setMerchantCode(requestDto.getMccCode());
		merchant.setMerchantAccountNo(requestDto.getMerchantAccountNo());
		merchant.setBranchCode(requestDto.getBranchCode());
		merchant.setMerchantCallbackURL("canarabank.com");
		merchant.setOrgCode("123");
		merchant.setActive(true);
		merchant.setSettlementBank("Canara Bank");
		merchant.setAadharNumber("999999999999");
		merchant.setStatus(MerchantRegStatus.SUCCESS_STATE_1.getStatus());
		merchant.setName(requestDto.getManagerName());
		merchant.setCustomerId(requestDto.getCustomerId());
		merchant.setMerchantType("UQR");
		
		merchant.setUniqueMid(
				"MER" + requestDto.getMerchantVPA().substring(0, requestDto.getMerchantVPA().lastIndexOf("@")));
		merchant.setContactNo(requestDto.getMerchantMobileNo());
		merchant.setUpdatedAt(LocalDateTime.now());
		merchant.setCreatedAt(LocalDateTime.now());
		
		merchant = merchantsRepo.save(merchant);
		log.info("data added successfully in DB FOR {}",merchant.getMerchantVPA());
		log.info("data Addedd {} ",merchant.toString());
		pdateAccountDetailsResponse = new UpdateAccountDetailsResponse(); 
		pdateAccountDetailsResponse.setAccount_no(merchant.getMerchantAccountNo());
		pdateAccountDetailsResponse.setMsg("Request Sent Successfully");
	    
	     return pdateAccountDetailsResponse;
	}

/*	
	public static void main(String[] args) {
	    try {
	     String secret = "CB_h@$H_k(y_2019";
	     String message = "{\"Dpcd\":\"792\",\"Account\":\"0473111033572\"}";
	    String hash=getHash(secret,message).toUpperCase();	    
	     System.out.println(hash);
	    }
	    catch (Exception e){
	     System.out.println("Error");
	    }
	    String S1="test";
	    String S2=null;
	    
	    System.out.println("Helloo"+S1+S2);
	   
	}*/

	@Override
	public Merchants getAccountDetails(String Message,AccountSearchRequestDto accountSearchRequestDto) {
		Merchants merchantdetails=null;
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
    	String dateString = simpleDateFormat.format(new Date());
		String getauth=getEncripted();
		HttpHeaders headder=new HttpHeaders();
		try {
			System.out.println("Date "+Message);
			encryptedString = EncAESGSM.encryptIV(Message.getBytes(),secretKeys.getBytes(),keyIV.getBytes()); 	
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String hashvalue=getHash(key,Message).toUpperCase();
		headder.set("Authorization", "Basic "+getauth);
		headder.set("Content-Type", "application/json");
		SASReq reqResp=new SASReq();
		reqResp.setVENDOR_ID("BHIM_USER");
		reqResp.setMETHOD_ID("001");
		reqResp.setREQUEST_ENC(encryptedString);
		reqResp.setREQUEST_DIGEST(hashvalue);
		reqResp.setREQUEST_DATE_TIME(dateString);
		//AccountSearchRequestDto accountSearchRequestDto=null;
		//accountSearchRequestDto = Utility.getReqJSONDate(Message);
		HttpEntity<SASReq> httpEntity = new HttpEntity<>(reqResp,headder);
		log.info("URL FOR KYC REQ {}",KYCURL.trim());
		log.info("Headder for KYC {}",headder);
		//log.info("REQ TO KYC {} ",reqResp.toString());
		System.out.println("DATA REq {}"+reqResp.toString());
		System.out.println("HEADDER DATA ******"+headder);
		String response = httpClient.httpExchange(KYCURL.trim(), HttpMethod.POST, httpEntity, String.class);
		//String response="{\"VENDOR_ID\":\"TEST_USER\",\"METHOD_ID\":\"001\",\"RESPONSE_CODE\":\"1\",\"RESPONSE_ENC\":\"qtQz+pXNLq1OEV2Sz7udaymTC02JAB7F7rJ3AMs90mm18WJm12MHxqEysTXshG2O7LzbkoFVsgUJBTO9Vw9K/LWm2CUks/+z38iafEdNABpfx78S1RY4TPgQAemitZLM9TbC6WXlBE3m7SgPxXP8hiwrkFpKfVUophKLJCDhz5xunCAJpo18+QNoRNhRVWzfPDY7bs/xXBtMlEOkvlmNE8sJV9tFymOh5OjY6Fmav8ittxpnorYYkCzObU6DBXBEPnZw/0hiNXKaHG3a+BiLAun2gqpHGhIOwSSKz4/k0vLlJNs78xcDynICJQAEQOgzpKouhA7uDqTWvwd+eVnwjdRzE5coBeLJEyn2tLTUvAC1kXUc9yuSWJ+9R3PjfNsdRliHIWXIxkni+AfvmvtG/P7loTSQV2Ukj1uTFO2bMUCDa9lhubx7yO9Wj+79lQ9wqv6fsqz2goH22BBo/AMZvah/l246SAPSqLzWrXn0EMm/+johzuVenghuwCQBqzWJlVpJDhAb4fkZmp0vQ1W3Enq2yJLYSZ2t+GcDzku8oKa/sQNgMRvYU/JSqQoglml0kvjSrt4cRdn424v8n1nxyk3DYMkcS7jz0wegKFizoDriTLME60wqiYEk4ujQG11UuBMbw4XcoGHw2x1sNYnYL+fTvv/Rr0w5YrX1T7l9bMdPRwpwBNLehzrmHXngrFwxoQ3jTxMVfh+CsiJtbCymj2bx52Ukik3Q0Wo3bNXz7haaW+qytqD9RQqEmoPcIDHl8pwNjWPdM9F9fcsyPD36iLHX+FNdGkag92T5mQd487PV5Na2c51MPeTHdooYEtW4B5NaGiRDCvLi9U71ZkSvwQ9JQjyKuP2GaCqF6PBsIe1GiHaAvV35TEZg6fnlTPsRfbE0khBzt/r4MT+VvJnUdHaAFQiRcL2Cu2h7i+uesR+0IVbvljr3ePd6lpzbV9thcTn3j5BRfdEybkk3UOjRRrKJzoDMza9X9aupy+DgdP37/XpplIKz7Ou8U8I76EtMp/sEIWI3z1xy4odGxJekdRT1muhHu6Tt98PeTixFz2J1x9z5CpjW9rPCRg4nqokSAd12npnrtPVTuojp\",\"RESPONSE_DATE_TIME\":\"2019-08-31T12:17:03.4146119+05:30\"}";
		//String response="{\"VENDOR_ID\":\"BHIM_USER\", \"METHOD_ID\":\"001\", \"RESPONSE_ENC\":\"qtQz+pXNLq1OEV2Sz7udayGRAkiNAR/F8bwBHdYl2w6t6Q1O1zJZl8BLwCLmhHGe7aDLjpRHuWZ+YlDJNAw+jMWt1jk6po/Kvt+GYF0yZgxcqcx+1w4uOp10FeuvpIit6DuhgQrpBjHk6C4TxXPxnS4ynDc+M2ML6BKLLyD32IR0jS8FrupkrRhxRJhfOmnaKy4hf8D9VHxUwFi9vhmDdsIGQMpIphjO9ejOpGi5kca10m4R1sNngifBCV37FwQxTGRw7Ul4KWDjdAioi3yMZpiI4MU8bmYdzzz82Z7sser3ItE69WgBwXtjS39+MYxYptI5lAH0E6m3vBRtcT368NhpBeZQZuLYCDiVs6jBuWbL7GAA50bzN+66T3P1a9sLViOFVQbLzkiU9GbziuZX+/z3pzT2KRgrilL/dYLqNkCTd8cA3Mpsq5grnPr8gB19uYPw2bbu7eSX3gJo8wZpztlll3cgKWyjsa2w0wTpBcW0/E1IuOlbiB90z1ADoFb38kBPaHV4jZR2kYYuT0KjBXyoz47TQY3f4H9otDfF1bCisXsaMRzPWfFDugMzjWFig56svcgeUqeNvYmd/F/i20azZtQcWrDk1GTITj3JzkfhQagfiks3mo8u//q1YDNOzBwApOqmoh+dzgEaRvvJJf7Nqujco0k5AsiOKa4HCrklQhF3GNLTjjXuAR+bz1oo136RRAAVYAuVvy5obE3b9g32/xNQ6T+9oWUlaMuBh2aZW+O/uabmXBLi8v7MLzH56pwbjWTSOsdsGrVdMzjliKLc/kxFfC7d7X78hAN45bPW9bnFAYxGJPrTYYcGB9C+HespaEspCur4kSKVCDbGsQ9HRCeZp+uYH12F5upgIOROkmuVxSaINTkHmpXgSZZlGtdL/mVjuqO+ZGOBruu5GHmGFWTuDbKFtxoA1a7K63e0XTXkhDXxFu9i9YPdSsoKB1+GiuE1ZqJHGUA8P5O6Kr7mo46hpdJE6KCyuYidAO7t7Q0b+/+h++m3VcVYhiYzp4plTh8q001k8ps2t7+IO0K3zc1txMWGi7LeIkQlvBwXtdj9D4qfnyoExPN5uerQNJtjUDkaV6U=\",\"RESPONSE_DIGEST\":EACDCDFD9E5D050D3272A877D6D3802C7DD188C832468354427715EF14AF825C\", \"RESPONSE_DATE_TIME\":\"2019-11-12T12:21:17.4065772+05:30\", \"RESPONSE_CODE\":1}";
		log.info("RESPONCE from KYC System***** : {}", response);
		//SASResp KYCResp = new Gson().fromJson(response, SASResp.class);		
		System.out.println("RespDATA******"+response.trim());
		SASResp	KYCResp = Utility.getRespJSONDate(response.trim());
		log.info("RESP IN JSON {} ",KYCResp);
		if("1".equalsIgnoreCase(KYCResp.getRESPONSE_CODE())){
			log.info("GOT SUCCESS FROM KYC SYS{}  ",KYCResp.getRESPONSE_CODE());
			merchantdetails=new Merchants();
		String decriptedvalue=EncAESGSM.decryptVI(KYCResp.getRESPONSE_ENC(), secretKeys.getBytes(), keyIV.getBytes());
		log.info("GET VALUE OF KYC DETAILS {}",decriptedvalue);
		Gson gson = new Gson();
		List<MerchantKYC_Details> KYC_details = Arrays.asList(gson.fromJson(decriptedvalue, MerchantKYC_Details[].class));
		for(MerchantKYC_Details finalDetails : KYC_details) {
			log.info("inside FOR EATCH");
			merchantdetails.setCustomerId(finalDetails.getCOD_CUST_ID());
			String vpa=generateVpas(accountSearchRequestDto.getAccount(),finalDetails.getCOD_CUST_ID());
			merchantdetails.setMerchantVPA(vpa);
			merchantdetails.setMerchantMobileNo(finalDetails.getMOBILE_NUM());
			
			if(null!=finalDetails.getCUST_SHORT_NAME()) {
				merchantdetails.setMerchantOrgName(finalDetails.getCUST_FULL_NAME());
				merchantdetails.setManagerName(finalDetails.getCUST_FULL_NAME());
			}
			else {
				merchantdetails.setMerchantOrgName(finalDetails.getFIRST_NAME());
				merchantdetails.setManagerName(finalDetails.getFIRST_NAME());

			}
			
			merchantdetails.setApiBank("CANARA BANK");
			merchantdetails.setOperatorEmail(finalDetails.getEMAIL());
			merchantdetails.setMerchantAddress(finalDetails.getTXT_CUSTADR_ADD1()+finalDetails.getTXT_CUSTADR_ADD2()+finalDetails.getTXT_CUSTADR_ADD3());
			merchantdetails.setBranchCode(finalDetails.getCOD_CUST_ID());
			merchantdetails.setMerchantIFSCCode(finalDetails.getIFSC());
			/*merchantdetails.setStatus(0);
			merchantsRepo.saveAndFlush(merchantdetails);*/
			//String Merchtvpa=CheckVpaInUpi(vpa);
			//merchantdetails.setMerchantVPA(Merchtvpa);
		}
		log.info("Responce FROM KYC SYS DECRIPTED"+merchantdetails.toString());
		}
		return merchantdetails;
			}

	

	@Override
	public void Setparam(Merchants accountSerachResponse, AccountSearchRequestDto accountSearchRequestDto) {
		accountSerachResponse.setMerchantAccountNo(accountSearchRequestDto.getAccount());
		accountSerachResponse.setBranchCode(accountSearchRequestDto.getDpcd());
	}
	
	private String generateVpas(String accNo,String custId) {
		String vpa = "";
		vpa = accNo.substring(accNo.length()-6, accNo.length());
		String generateVpa = null;
		generateVpa = custId.concat(vpa).concat("@cnrb");
		return generateVpa;
	}
	
	
	private boolean vpaAlradyregistor(UpdateAccountDetailsDto requestDto) {
		log .info("getting vpa details from DB");
		Merchants merchantvpadata =merchantsRepo.findByMerchantVPA(requestDto.getMerchantVPA());
		if(merchantvpadata!=null) {
			return true;
		}
		return false;
	}
 
}
