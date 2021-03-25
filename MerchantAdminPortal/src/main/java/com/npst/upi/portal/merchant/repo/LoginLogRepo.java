package com.npst.upi.portal.merchant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npst.upi.portal.merchant.entity.Loginlog;

public interface LoginLogRepo extends JpaRepository<Loginlog, Long>  {

	Loginlog findByLoginToken(String tokenId);
}
