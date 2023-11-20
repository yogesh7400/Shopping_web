package com.abm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Retailer {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String location;
	    private String email;
	    private String password;
	    
	    
	    @OneToMany(mappedBy = "retailer", cascade  = CascadeType.ALL)
	    private List<Product> products = new ArrayList<>();
	    
	    public Retailer() {
			// TODO Auto-generated constructor stub
		}

		public Retailer(Long id, String name, String location, String email, String password, List<Product> products) {
			super();
			this.id = id;
			this.name = name;
			this.location = location;
			this.email = email;
			this.password = password;
			this.products = products;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}
	    
	    
	  
	    
	    
}
