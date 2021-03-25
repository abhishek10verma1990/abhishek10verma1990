package com.npst.upi.portal.merchant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npst.upi.portal.merchant.entity.MerchantsError;

public interface MerchantsErrorRepo extends JpaRepository<MerchantsError, Long>{

	List<MerchantsError> findByFileName(String fileName);
}
