/**
 * 
 */
package com.npst.upi.portal.merchant.utility;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	private static final String	SMS_QUEUE			= "SMS.QUEUE";

	private static final Logger	log	= LoggerFactory.getLogger(Util.class);

	public static void makeDir(String directoryName){
	   
	    File directory = new File(directoryName);
	    if (! directory.exists()){
	        directory.mkdir();
	        // If you require it to make the entire directory path including parents,
	        // use directory.mkdirs(); here instead.
	    }

	}

	public static synchronized String generateUniqueMId(String prefix) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append(System.currentTimeMillis());
		return sb.toString();
	}

	public static void sendSMS(String message, String contactNo, String sms) {
		log.trace("sending SMS :{} ", contactNo);
		if (message == null || Constants.CONST_BLANK.equalsIgnoreCase(message)) {
			log.info("message blank not sending SMS");
			return;
		}

		Message messagetosend = new Message();
		messagetosend.setMobileNo(contactNo);
		messagetosend.setType(sms);
		messagetosend.setMessage(message);
		String queueName = Util.SMS_QUEUE;
	//	RabbitMQSend.send(messagetosend, queueName);
		log.trace("End of Send SMS on {} : ", contactNo);		
	}
	
	public static String maskNumber(final String actNumber) {
		final String s = actNumber.replaceAll("\\D", "");
		final int start = 0;
		final int end = s.length() - 4;
		final String overlay = StringUtils.repeat("X", end - start);
		return StringUtils.overlay(s, overlay, start, end);
	}
	
}
