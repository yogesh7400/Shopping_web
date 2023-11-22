package com.abm.request;


public class LoginStatus extends Status {
	private Long retailerId;
	private String name;
	private long userId;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public Long getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	

}
