package com.abm.repo;         //new

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abm.entity.Product;
import com.abm.entity.Retailer;
import com.abm.entity.User;

public interface RetailerRepository extends JpaRepository<Retailer, Long> {

	//login
		public Optional<Retailer> findByEmailAndPassword(String email, String password);
		
		//register
		@Query("select count(c) from Retailer c where c.email = ?1")
		public Long findIfRetailerExists(String email);
		
		//fetch
		@Query("select p from Product p where p.retailer.id = ?1")
		public List<Product> findByRetailerId(Long retailerId);
		
		public Retailer findByEmail(String email);
			
	

}
