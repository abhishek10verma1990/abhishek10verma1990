/**
 * 
 *//*
package com.npst.upi.portal.merchant.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;
*//**
 * 
 * @author rahul
 * Filter allowing custom headers and all origins request to accept
 * Check Spring provided org.springframework.web.filter.CorsFilter
 *
 *//*
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		    log.info("CORS Filter, reqData: {}, respData : {}", request, response);
			if(CorsUtils.isCorsRequest(request)) 
			{
				response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, GET, OPTIONS, DELETE");
		        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
		        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, Content-Type, Accept, X-Requested-With, Authorization, authorization, username, tenant_id");
		        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-AUTH-TOKEN");
		        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

		        if(!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
		        	filterChain.doFilter(request, response);
		        }
			} 
			else 
			{
				filterChain.doFilter(request, response);
			}
	        
	}
}
*/