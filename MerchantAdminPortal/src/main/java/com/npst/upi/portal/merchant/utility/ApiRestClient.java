/**
 * 
 */
package com.npst.upi.portal.merchant.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rahul Chaudhary
 *
 */
@Component
public class ApiRestClient {
	
	@Autowired
	private RestTemplate httpClient;

	public String httpExchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<?> responseType) {
		ResponseEntity<?> response = httpClient.exchange(url, method, requestEntity, responseType);
		return (String) response.getBody();
	}
	

}
