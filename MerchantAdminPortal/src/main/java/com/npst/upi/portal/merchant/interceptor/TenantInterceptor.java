package com.npst.upi.portal.merchant.interceptor;
import org.springframework.http.MediaType; 
import org.springframework.stereotype.Component; 
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.npst.upi.portal.merchant.utility.TenantContext;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
/**
 *  
 * @author Rahul Chaudhary
 *
 */
@Component 
public class TenantInterceptor extends HandlerInterceptorAdapter { 
 
  private static final String TENANT_HEADER = "tenant_id";
 
  @Override 
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String tenantHeader = request.getHeader(TENANT_HEADER);
    boolean tenantSet = false;
    if(tenantHeader != null && !tenantHeader.isEmpty()) {
      TenantContext.setCurrentTenant(tenantHeader);
      tenantSet = true;
    } else { 
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write("{\"status\": \"0\", \"statusCode\": \"ERROR\", \"statusMsg\": \"No tenant supplied\", \"response\": \"\"}");
      response.getWriter().flush();
    } 
    return tenantSet;
  } 
 
  @Override 
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    TenantContext.clear(); 
  } 
 
} 