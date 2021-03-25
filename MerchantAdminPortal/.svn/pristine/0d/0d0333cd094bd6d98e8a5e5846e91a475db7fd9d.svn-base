/**
 * 
 */
package com.npst.upi.portal.merchant.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.npst.upi.portal.merchant.exception.ApplicationException;
import com.npst.upi.portal.merchant.service.MerchantUPISwitchRegistrationService;

/**
 * @author Rahul Chaudhary
 *
 */
@EnableScheduling
@Component
public class MerchantUPISwitchRegistrationScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(MerchantUPISwitchRegistrationScheduler.class);
	
	@Autowired
	private MerchantUPISwitchRegistrationService merchantUPISwitchRegistrationService;
	
	@Scheduled(initialDelayString="${REGISTER_MERCHANTS_INITIALDELAY}", fixedDelayString="${REGISTER_MERCHANTS_FIXEDDELAY}")
	public void registerMerchants() {
		try {
			log.info("Sheduler started....");
			merchantUPISwitchRegistrationService.merchantUPISwitchRegistration();
		} catch (ApplicationException e) {
			e.printStackTrace();
			log.error("Error : {}", e);
		}
	}

}
