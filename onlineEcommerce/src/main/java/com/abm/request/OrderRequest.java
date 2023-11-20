package com.abm.request;

import com.abm.entity.Address;
import com.abm.entity.User;

public class OrderRequest {
	
	  private User user;
	    private Address shippingAddress;
	    
	    
	    public OrderRequest() {
			// TODO Auto-generated constructor stub
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public Address getShippingAddress() {
			return shippingAddress;
		}


		public void setShippingAddress(Address shippingAddress) {
			this.shippingAddress = shippingAddress;
		}
	    
	    
	    

}
