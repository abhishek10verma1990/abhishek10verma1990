package com.npst.upi.portal.merchant.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.npst.upi.portal.merchant.dto.ResponseStatus;
import com.npst.upi.portal.merchant.dto.SimpleDto;
import com.npst.upi.portal.merchant.entity.MerchantsTxn;
import com.npst.upi.portal.merchant.exception.UPIPortalException;
import com.npst.upi.portal.merchant.repo.MerchantsTxnRepo;
import com.npst.upi.portal.merchant.response.GetPaginatedServiceResp;
import com.npst.upi.portal.merchant.service.TransactionReconciliationService;
import com.npst.upi.portal.merchant.utility.Utility;

/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Service
public class TransactionReconciliationServiceImpl implements TransactionReconciliationService {
	private static final Logger log = LoggerFactory.getLogger(TransactionReconciliationServiceImpl.class);

	@Value("${BenificiaryBank}")
	private String benificiaryBank;
	@Value("${BenificiaryIFSC}")
	private String benificiaryIFSC;

	@Autowired
	private MerchantsTxnRepo reconciliationRepo;

	@Override
	public GetPaginatedServiceResp<com.npst.upi.portal.merchant.dto.MerchantsTxn> getReconciliationByDates(SimpleDto dto)
			throws UPIPortalException {
		return getReconciliationByDatesWithEntityResponse(dto);
	}

	private GetPaginatedServiceResp<com.npst.upi.portal.merchant.dto.MerchantsTxn> getReconciliationByDatesWithEntityResponse(
			SimpleDto dto) throws UPIPortalException {
		GetPaginatedServiceResp<com.npst.upi.portal.merchant.dto.MerchantsTxn> out = new GetPaginatedServiceResp<>();
		try {
			Page<MerchantsTxn> page = null;

			if (!StringUtils.isEmpty(dto.getStartDate()) && !StringUtils.isEmpty(dto.getEndDate()) && !StringUtils.isEmpty(dto.getQrType())) {

				page = reconciliationRepo.findByCreatedAtBetweenAndMerchantType(dto.getStartDate(), dto.getEndDate(),
						new PageRequest(dto.getPage(), dto.getSize()), dto.getQrType());
			} else if (!StringUtils.isEmpty(dto.getStartDate()) && !StringUtils.isEmpty(dto.getEndDate())) {

				page = reconciliationRepo.findByCreatedAtBetween(dto.getStartDate(), dto.getEndDate(),
						new PageRequest(dto.getPage(), dto.getSize()));

			}
			log.info("Records are:{}",page);
			if (page != null) {
				List<com.npst.upi.portal.merchant.dto.MerchantsTxn> merchantsTxn = page.getContent().stream().map(item -> convertToDto(item)).collect(Collectors.toList());
				out.setList(merchantsTxn);
				out.setPageOb(Utility.getPageInfo(page));
				out.setPagingSupport(true);

			} else {
				throw new UPIPortalException(ResponseStatus.ERROR.getStatus_msg(),
						ResponseStatus.ERROR.getStatus_code());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UPIPortalException(ResponseStatus.ERROR.getStatus_msg(), ResponseStatus.ERROR.getStatus_code());
		}

		return out;
	}

	/*private List<TransactionReconciliationDto> mapEntityToDto(List<MerchantsTxn> list) {
		List<TransactionReconciliationDto> outList = new ArrayList<TransactionReconciliationDto>();
		if (list == null || list.size() == 0) {
			return outList;

		}
		try {
			TransactionReconciliationDto out = null;
			for (MerchantsTxn ob : list) {
				if (ob == null)
					continue;
				out = new TransactionReconciliationDto();
				out.setBene_Account_No(ob.getPayeeAccountNo());
				out.setBene_Bank(benificiaryBank);
				out.setBene_IFSC(benificiaryIFSC);
				out.setBene_VPA(ob.getPayeeAdd());
				out.setRemitter_Account_No(ob.getPayerAccountNo());
				out.setRemitter_Mobile_No(ob.getPayerMobileNo());
				out.setRemitter_VPA(ob.getPayerVpa());
				out.setRrn(ob.getRrn());
				out.setStatus_received_from_NPCI(ob.getNarration());
				out.setCreatedDate(ob.getCreatedAt().toString());
				outList.add(out);
			}
		} catch (Exception e) {
			log.error("Error : {}", e);
			e.printStackTrace();
		}
		return outList;
	}*/
	
	private com.npst.upi.portal.merchant.dto.MerchantsTxn convertToDto(MerchantsTxn merchanttxn) {
		log.trace("create Merchant TXN  Entity {}", merchanttxn);

			com.npst.upi.portal.merchant.dto.MerchantsTxn merchantentyty=new com.npst.upi.portal.merchant.dto.MerchantsTxn();
			merchantentyty.setAmount(merchanttxn.getAmount());
			merchantentyty.setCustomerRefId(merchanttxn.getCustomerRefId());
			merchantentyty.setErrorCode(merchanttxn.getErrorCode());
			merchantentyty.setNarration(merchanttxn.getNarration());
			merchantentyty.setPayeeAccountName(merchanttxn.getPayeeAccountName());
			merchantentyty.setNpciErrorCode(merchanttxn.getNpciErrorCode());
			merchantentyty.setPayeeAccountNo(merchanttxn.getPayeeAccountNo());
			merchantentyty.setPayeeIfsc(merchanttxn.getPayeeIfsc());
			merchantentyty.setPayeeAccountName(merchanttxn.getPayeeAccountName());
			merchantentyty.setPayeeAdd(merchanttxn.getPayeeAccountName());
			merchantentyty.setPayeeCode(merchanttxn.getPayeeCode());
			merchantentyty.setPayeeMobileNo(merchanttxn.getPayeeMobileNo());
			merchantentyty.setPayerIfscCode(merchanttxn.getPayerIfscCode());
			merchantentyty.setPayerAccountNo(merchanttxn.getPayerAccountNo());
			merchantentyty.setPayerMobileNo(merchanttxn.getPayerMobileNo());
			merchantentyty.setPayerName(merchanttxn.getPayerName());
			merchantentyty.setPayerVpa(merchanttxn.getPayerVpa());
			merchantentyty.setSettlementStatus(merchanttxn.getSettlementStatus());
			//merchantentyty.setPayeeAccountDetails(merchanttxn.getPayeeAccountNo()+"|"+merchanttxn.getPayeeIfsc()+"|"+merchanttxn.getPayeeAccountName());
			merchantentyty.setTxnType(merchanttxn.getTxnType());
			merchantentyty.setTxnId(merchanttxn.getTxnId());
			merchantentyty.setBankId(merchanttxn.getBankId());
			merchantentyty.setCreatedAt(merchanttxn.getCreatedAt().toString());
			merchantentyty.setChannel("MOB");
			//merchantentyty.setMerchantType(merchanttxn.getMerchantType());
			merchantentyty.setCustomerRefId(merchanttxn.getCustomerRefId());
			merchantentyty.setStatus(merchanttxn.getStatus());
			merchantentyty.setTxnId(merchanttxn.getTxnId());

			return merchantentyty;
		}

}
