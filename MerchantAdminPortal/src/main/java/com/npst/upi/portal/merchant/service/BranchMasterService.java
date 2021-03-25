/**
 * 
 */
package com.npst.upi.portal.merchant.service;

import java.util.List;

import com.npst.upi.portal.merchant.dto.BranchMasterRequest;

/**
 * @author Rahul Chaudhary
 *
 */
public interface BranchMasterService {

	List<String> findByBankid(BranchMasterRequest branchMasterRequest);

}
