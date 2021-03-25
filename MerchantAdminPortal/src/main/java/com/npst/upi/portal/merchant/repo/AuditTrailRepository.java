/**
 * 
 */
package com.npst.upi.portal.merchant.repo;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.npst.upi.portal.merchant.entity.AuditTrail;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {
}
