/**
 * 
 */
package com.npst.upi.portal.merchant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npst.upi.portal.merchant.entity.BranchMaster;

/**
 * @author Rahul Chaudhary
 *
 */
@Repository
public interface BranchMasterRepo extends JpaRepository<BranchMaster, Long> {

	List<BranchMaster> findByBankid(String bankid);
	List<BranchMaster> findByBranchcode(String branchCode);

}
