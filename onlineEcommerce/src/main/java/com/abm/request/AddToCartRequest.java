package com.abm.request;



public class AddToCartRequest {
	
	 private AddItemRequest addItemRequest;
	    private String userEmail;
	    
	    
public AddToCartRequest() {
	// TODO Auto-generated constructor stub
}


public AddItemRequest getAddItemRequest() {
	return addItemRequest;
}


public void setAddItemRequest(AddItemRequest addItemRequest) {
	this.addItemRequest = addItemRequest;
}


public String getUserEmail() {
	return userEmail;
}


public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}



}
   
