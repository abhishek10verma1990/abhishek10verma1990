package com.npst.upi.portal.merchant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.npst.upi.portal.merchant.dto.AuditTrailDto;
import com.npst.upi.portal.merchant.entity.AuditTrail;
import com.npst.upi.portal.merchant.repo.AuditTrailRepository;
import com.npst.upi.portal.merchant.service.AuditTrailService;

/**
 * 
 * @author shujat khan
 *
 */
@Service
public class AuditTrailServiceImpl implements AuditTrailService {

	private static final Logger log = LoggerFactory.getLogger(AuditTrailService.class);

	@Autowired
	AuditTrailRepository auditTrailRepository;


	public AuditTrailDto saveOrUpdate(AuditTrailDto auditTrailDto) {
		
			AuditTrail auditTrail = auditTrailRepository.save(convertToEntity(auditTrailDto));
			return convertToDto(auditTrail);
		
	}
	
	public static AuditTrailDto convertToDto(AuditTrail auditTrail) {
		AuditTrailDto auditTrailDto = new AuditTrailDto();
		auditTrailDto.setId(auditTrail.getId());
		auditTrailDto.setModifiedOn(auditTrail.getModifiedAt());
		auditTrailDto.setModulename(auditTrail.getModulename());
		auditTrailDto.setOperation(auditTrail.getOperation());
		auditTrailDto.setResponse(auditTrail.getResponse());
		auditTrailDto.setRequest(auditTrail.getRequest());
		auditTrailDto.setUsername(auditTrail.getUsername());
		return auditTrailDto;
	}

	public static AuditTrail convertToEntity(AuditTrailDto auditTrailDto) {
		AuditTrail auditTrail = new AuditTrail();
		auditTrail.setId(auditTrailDto.getId());
		auditTrail.setModifiedAt(auditTrailDto.getModifiedOn());
		auditTrail.setModulename(auditTrailDto.getModulename());
		auditTrail.setOperation(auditTrailDto.getOperation());
		auditTrail.setResponse(auditTrailDto.getResponse().toString());
		auditTrail.setRequest(auditTrailDto.getRequest().toString());
		auditTrail.setUsername(auditTrailDto.getUsername());
		return auditTrail;
	}
	
}
