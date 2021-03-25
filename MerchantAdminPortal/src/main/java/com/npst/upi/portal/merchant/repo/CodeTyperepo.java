package com.npst.upi.portal.merchant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.npst.upi.portal.merchant.entity.Code;
import com.npst.upi.portal.merchant.entity.Merchants;

public interface CodeTyperepo extends JpaRepository<Code, Long>{
	
	List<Code> findByCodetypeAndStatus(String codetype,byte status);
}
