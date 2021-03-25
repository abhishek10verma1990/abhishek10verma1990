package com.npst.upi.portal.merchant.dto;

public class CommonRequest <T>{

	private String vendor_code;
	private String session_id;
	private String request_type;
	T request_data;
	
	public T getRequest_data() {
		return request_data;
	}
	public void setRequest_data(T request_data) {
		this.request_data = request_data;
	}
	public String getVendor_code() {
		return vendor_code;
	}
	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	@Override
	public String toString() {
		return "CommonRequest [vendor_code=" + vendor_code + ", session_id=" + session_id + ", request_type="
				+ request_type + ", request_data=" + request_data + "]";
	}
	
	
}
