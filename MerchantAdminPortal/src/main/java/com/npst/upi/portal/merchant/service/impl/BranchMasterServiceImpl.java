/**
 * 
 */
package com.npst.upi.portal.merchant.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npst.upi.portal.merchant.dto.BranchMasterRequest;
import com.npst.upi.portal.merchant.entity.BranchMaster;
import com.npst.upi.portal.merchant.repo.BranchMasterRepo;
import com.npst.upi.portal.merchant.service.BranchMasterService;

/**
 * @author Rahul Chaudhary
 *
 */
@Service
@Transactional
public class BranchMasterServiceImpl implements BranchMasterService {

	@Autowired
	private BranchMasterRepo branchMasterRepo;
	@Override
	public List<String> findByBankid(BranchMasterRequest branchMasterRequest) {
		List<BranchMaster> branches = branchMasterRepo.findByBankid("COSMOS");
		return branches.stream().map(b -> b.getBranchcode()).collect(Collectors.toList());
	}
	
	

}
