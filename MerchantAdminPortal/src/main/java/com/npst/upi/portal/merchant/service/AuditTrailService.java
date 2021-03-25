package com.npst.upi.portal.merchant.service;

import com.npst.upi.portal.merchant.dto.AuditTrailDto;

public interface AuditTrailService {

	public AuditTrailDto saveOrUpdate(AuditTrailDto auditTrailDto);
}
