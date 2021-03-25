package com.npst.upi.portal.merchant.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npst.upi.portal.merchant.dao.MerchantTxnDao;
import com.npst.upi.portal.merchant.dto.MerchantsTxn;
import com.npst.upi.portal.merchant.repo.MerchantsTxnRepo;
@Service
public class MerchantTxnDaoImpl implements MerchantTxnDao {
	private static final Logger	log				= LoggerFactory.getLogger(MerchantTxnDaoImpl.class);

	@Autowired
	private MerchantsTxnRepo merchantRepository;
	
	@Override
	public void saveMerchantTxn( final MerchantsTxn merchanttxn) {
		log.trace("save Merchant Txn {}", merchanttxn);
		try{
			if(null!=merchanttxn){
			com.npst.upi.portal.merchant.entity.MerchantsTxn merchant = (com.npst.upi.portal.merchant.entity.MerchantsTxn) merchantRepository.save(createMerchantEntity(merchanttxn));
			log.info("Merchant Txn With id{}, and Type{} save Successfully ",merchant.getId(),merchant.getTxnType());
			}
			
			}catch(Exception e){
			log.error("error on save MerchantTXN message {}",e);
		}
	}
	private com.npst.upi.portal.merchant.entity.MerchantsTxn createMerchantEntity(MerchantsTxn merchanttxn) {
		log.trace("create Merchant TXN  Entity {}", merchanttxn.toString());

			com.npst.upi.portal.merchant.entity.MerchantsTxn merchantentyty=new com.npst.upi.portal.merchant.entity.MerchantsTxn();
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
			merchantentyty.setPayeeAccountDetails(merchanttxn.getPayeeAccountNo()+"|"+merchanttxn.getPayeeIfsc()+"|"+merchanttxn.getPayeeAccountName());
			merchantentyty.setTxnType(merchanttxn.getTxnType());
			merchantentyty.setTxnId(merchanttxn.getTxnId());
			merchantentyty.setBankId(merchanttxn.getBankId());
			merchantentyty.setCreatedAt(LocalDateTime.now());
			merchantentyty.setChannel("MOB");
			merchantentyty.setMerchantType(merchanttxn.isBharatQR()?"BharatQR":"BhimQR");
			merchantentyty.setRrn(merchanttxn.getCustomerRefId());
			merchantentyty.setStatus(merchanttxn.getStatus());
			merchantentyty.setUpiTxnId(merchanttxn.getTxnId());
			return merchantentyty;
		}
		

}
