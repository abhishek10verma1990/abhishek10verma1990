package com.npst.upi.portal.merchant.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.npst.upi.portal.merchant.entity.Merchants;

public interface MerchantsRepo extends JpaRepository<Merchants, Long>{

	List<Merchants> findByFileNameAndStatus(String fileName, int state);
	Merchants findByMerchantVPA(String vpa);
	List<Merchants> findFirstByMerchantVPA(String merchantVPA);
	long countFirstByMerchantVPA(String merchantVPA);
	long countFirstByMerchantVPAAndMerchantType(String merchantVPA, String merchantType);
	List<Merchants> findByStatus(int status);
	List<Merchants> findByBranchCodeAndMerchantType(String branchcode, String qrtype);
	List<Merchants> findByBranchCodeAndMerchantTypeAndStatus(String branchcode, String qrtype, int status);
	Merchants findByMerchantVPAAndMerchantType(String vpa, String merchantType);
	Merchants findByMerchantVPAAndMerchantTypeAndActive(String vpa, String merchantType, Boolean active);
	@Transactional
	@Modifying
	@Query(value = "update merchants set status=?1 WHERE merchant_vpa=?2", nativeQuery = true)
	void updatemerchantStatus(Integer status, String merchantVPA);
	
	//Merchants findByMerchantAccountNo(String accountnum);
	List<Merchants> findByMerchantAccountNo(String accountnums);
	
	Merchants findByMerchantAccountNoAndMerchantVPA(String accountnum,String vpa);
	
	//@Query(value = "select *from merchants where status=?1 or status=?2", nativeQuery = true)
		@Query(value = "SELECT distinct d.customer_id , d.name, d.merchant_account_no, d.merchant_vpa, c.value as statusvalue, c.code as statuscode FROM merchants d INNER JOIN CODE c ON c.code=d.status WHERE d.status=1 or d.status=6 and c.codetype='STATUS_TYPE'", nativeQuery = true)
		List<Object[]> getByStatus();


	@Query(value = "SELECT * FROM MERCHANTS m where MERCHANT_VPA=?1 And MERCHANT_ACCOUNT_NO=?2", nativeQuery = true)
    List<Merchants> findByMerchantVpaAndMerchantAccountNum(String merchantVPA,String merchantAccount);
	
	@Query(value = "select distinct m.mcccode, m.customer_id, m.name, m.merchant_account_no, m.merchant_vpa, m.merchant_address, m.merchant_mobile_no, m.remarks, m.status, m.USER_FLAG, m.DELEVERED, m.ADDITIONAL_MOB_NO, c.value from merchants m INNER JOIN CODE c on m.status=c.code and c.codetype='STATUS_TYPE'", nativeQuery = true)
	List<Object[]> getById();
	
	
	@Query(value = "select * FROM merchants where merchant_account_no=?1 and status=?2", nativeQuery = true)
	List<Merchants> findByMerchantAccountNoandstatus(String qrcoderequest, Integer status);
	
	
	List<Merchants> findByMerchantAccountNoOrBranchCode(String accountNo,String branchCode);
	
	List<Merchants> findByMerchantAccountNoAndStatusOrBranchCodeAndStatus(String accountNo,int status,String branchCode,int statu1);
}