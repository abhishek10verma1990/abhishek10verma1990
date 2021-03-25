package com.npst.upi.portal.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.dto.AuditTrailDto;
import com.npst.upi.portal.merchant.dto.VPAGenrateDto;
import com.npst.upi.portal.merchant.entity.Code;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.repo.CodeTyperepo;
import com.npst.upi.portal.merchant.repo.MerchantsRepo;
import com.npst.upi.portal.merchant.service.CodeResponseDto;
import com.npst.upi.portal.merchant.service.CodeType;
@Service
public class CodeTypeImple implements CodeType{
    @Autowired
	CodeTyperepo repo;
    
    @Autowired
    MerchantsRepo merchantRepo;
    
    @Autowired
    AuditTrailServiceImpl audittrailserviceimpl;
    
	@Override
	public List<CodeResponseDto> getCodeDetails(String codetype) {
	
		List<CodeResponseDto> codeResponseDtolist =  new ArrayList<>();
	    List<Code> code = repo.findByCodetypeAndStatus(codetype,(byte)1);
	    CodeResponseDto codeResponseDto = null;
        try {
        	for (Code code2 : code) {
        		codeResponseDto=new CodeResponseDto();
        		codeResponseDto.setCode(code2.getCode());
        		codeResponseDto.setCodetype(code2.getCodetype());
        		codeResponseDto.setDescription(code2.getDescription());
        		codeResponseDto.setDisplayorder(code2.getDisplayorder());
        		codeResponseDto.setStatus(code2.getStatus());
    			codeResponseDtolist.add(codeResponseDto) ; 
    		}
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return codeResponseDtolist;
	}
	
	public String generateVpa(VPAGenrateDto request) {
		String vpa = "";
		vpa = request.getMerchantAccountNo().substring(0, 5);
		String generateVpa = null;
		generateVpa = request.getCustomerId().concat(vpa).concat("@cnrb");
		AuditTrailDto audittraildto=new AuditTrailDto();
		audittraildto.setRequest(request);
		audittraildto.setResponse(generateVpa);
		audittrailserviceimpl.saveOrUpdate(audittraildto);
		return generateVpa;
	}
	
	public String generateVpas(String custId, String accNo) {
		String vpa = "";
		vpa = accNo.substring(0, 5);
		String generateVpa = null;
		generateVpa = custId.concat(vpa).concat("@cnrb");
		
		return generateVpa;
	}
	public List<String> generateSuffixVpa(VPAGenrateDto merchantVPA) {
		List<String> list = new ArrayList();
		String suffixVpa = "";
		String vpa = merchantVPA.getMerchantVPA();
		vpa = vpa.split("@")[0];
		for(int i = 1; i<=merchantVPA.getAdditionalBhimQr(); i++) {
			suffixVpa = vpa.concat("_"+i+"@canmb");
			
		     list.add(suffixVpa);
		}
		AuditTrailDto audittraildto=new AuditTrailDto();
		audittraildto.setRequest(merchantVPA);
		audittraildto.setResponse(list);
		audittrailserviceimpl.saveOrUpdate(audittraildto);
		return list;
	}
}