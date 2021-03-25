package com.npst.upi.portal.merchant.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


import com.npst.upi.portal.merchant.entity.MccCode;

public interface MccCodeRepo extends JpaRepository<MccCode, Long>{
	
 public	List<MccCode> findAllByOrderByCode();


}
