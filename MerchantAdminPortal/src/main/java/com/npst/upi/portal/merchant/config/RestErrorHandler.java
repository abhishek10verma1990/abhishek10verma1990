package com.npst.upi.portal.merchant.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * @author npst
 * Rest Template Error Handler
 *
 */
@Component
public class RestErrorHandler extends DefaultResponseErrorHandler {
	private static final Logger log = LoggerFactory.getLogger(RestErrorHandler.class);
	 @Override
	  public void handleError(ClientHttpResponse response) throws IOException {
	    // your error handling here
		 log.error("Http status : {}", response.getStatusText());
		 log.error("Http response : {}", response);
	  }
}