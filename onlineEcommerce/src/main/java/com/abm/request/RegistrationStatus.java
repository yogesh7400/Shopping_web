package com.abm.request;


public class RegistrationStatus extends Status{
	
	private long retailerId;
	private long userId;
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(long retailerId) {
		this.retailerId = retailerId;
	}
	
	


}
