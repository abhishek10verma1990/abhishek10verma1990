package com.npst.upi.portal.merchant.repo;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.npst.upi.portal.merchant.entity.MerchantsTxn;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
public interface MerchantsTxnRepo extends JpaRepository<MerchantsTxn, Long> {
	/*Page<MerchantsTxn> findByCreatedAtBetweenAndPayerVpaOrPayeeAddAndMerchantType(LocalDateTime startDate, LocalDateTime endDate,
			String payerVpa, String payeeVpa, Pageable page, String qrtype);*/

	/*Page<MerchantsTxn> findByPayerVpaOrPayeeAddAndMerchantType(String payerVpa, String payeeVpa, Pageable page, String qrtype);

	Page<MerchantsTxn> findByRrnAndMerchantType(String rrn, Pageable page, String qrtype);
	
	Page<MerchantsTxn> findByRrn(String rrn, Pageable page);*/
	
	Page<MerchantsTxn> findByCreatedAtBetweenAndMerchantType(LocalDateTime startDate, LocalDateTime endDate, Pageable page, String qrtype);

	Page<MerchantsTxn> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable page);

}
