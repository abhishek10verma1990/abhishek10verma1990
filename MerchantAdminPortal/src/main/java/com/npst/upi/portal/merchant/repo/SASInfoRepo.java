package com.npst.upi.portal.merchant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npst.upi.portal.merchant.entity.SASInfo;
import java.lang.String;
import java.util.List;

@Repository
public interface SASInfoRepo extends JpaRepository<SASInfo, Long> {
	
	
	SASInfo findByStNo(String stNo);

}
