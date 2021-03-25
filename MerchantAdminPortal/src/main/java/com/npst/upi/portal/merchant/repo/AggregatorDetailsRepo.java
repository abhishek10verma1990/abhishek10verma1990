/**
 * 
 */
package com.npst.upi.portal.merchant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npst.upi.portal.merchant.entity.AggregatorDetails;

/**
 * @author Rahul Chaudhary
 *
 */
@Repository
public interface AggregatorDetailsRepo extends JpaRepository<AggregatorDetails, Long>  {

}
