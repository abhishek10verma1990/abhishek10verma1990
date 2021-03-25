package com.npst.upi.portal.merchant.dto;

public class MailingDetials {


	private String address_line1;
	private String address_line2;
	private String address_line3;
	private String city;
	private String state;
	private String country;
	private String pin;
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public String getAddress_line3() {
		return address_line3;
	}
	public void setAddress_line3(String address_line3) {
		this.address_line3 = address_line3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "MailingDetials [address_line1=" + address_line1 + ", address_line2=" + address_line2
				+ ", address_line3=" + address_line3 + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", pin=" + pin + "]";
	}
	

}
