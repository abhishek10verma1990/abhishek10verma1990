package com.npst.upi.portal.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npst.upi.portal.merchant.entity.MccCode;
import com.npst.upi.portal.merchant.repo.MccCodeRepo;
import com.npst.upi.portal.merchant.service.MccCodeService;


/**
 * @author Sweta Pandey
 *
 */

@Service
@Transactional
public class MccCodeServiceImpl implements MccCodeService{

	
	@Autowired
	private MccCodeRepo mccRepo;	
	
	
	@Override
	public List<MccCode> getAllMccCodes() { 
		List<MccCode> retrievedcodes=new ArrayList<>();
		List<MccCode> mCodes = mccRepo.findAllByOrderByCode();
		if(!mCodes.isEmpty()){}
		for(MccCode cde:mCodes){
			MccCode c=new MccCode();
			c.setId(cde.getId());
			c.setCode(cde.getCode());
			c.setCreatedAt(cde.getCreatedAt());
			c.setDescription(cde.getDescription());
			c.setIsActive(cde.getIsActive());
			c.setMccCategory(cde.getMccCategory());
			retrievedcodes.add(c);
		}
		
		return retrievedcodes;
	}

}
