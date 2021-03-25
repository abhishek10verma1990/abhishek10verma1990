package com.npst.upi.portal.merchant.utility;

/**
 * 
 * @author Rahul Chaudhary
 *
 */
public class TenantContext {

	private TenantContext() {

	}

	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(String tenant) {
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {
		return currentTenant.get();
	}

	public static void clear() {
		currentTenant.set(null);
	}

}