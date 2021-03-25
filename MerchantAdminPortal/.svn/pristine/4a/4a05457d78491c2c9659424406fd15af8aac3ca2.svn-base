/**
 * 
 */
package com.npst.upi.portal.merchant.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npst.upi.portal.merchant.constant.Constants;

/**
 * @author Rahul Chaudhary
 *
 */
@RestController
@RequestMapping(Constants.BASEURL)
public class MerchantUpiSwitchRegistrationController {

	@RequestMapping(path=Constants.REGISTRATION, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody Map<?,?> map) {
		System.out.println("map : "+map);
		return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
	}
}
