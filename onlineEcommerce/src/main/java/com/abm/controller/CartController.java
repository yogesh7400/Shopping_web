package com.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.Cart;
import com.abm.entity.CartItem;
import com.abm.entity.User;
import com.abm.exception.ProductException;
import com.abm.request.AddItemRequest;
import com.abm.service.CartServiceImplementation;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	
	  @Autowired
	    private CartServiceImplementation cartService;

	    @PostMapping("/create/{userId}")
	    public ResponseEntity<Cart> createCart(@PathVariable User userId) {
	        Cart createdCart = cartService.createCart(userId);
	        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
	    }

	    @GetMapping("/user/{userId}")
	    public ResponseEntity<Cart> getUserCart(@PathVariable Long userId) {
	        Cart userCart = cartService.findUserCart(userId);
	        return new ResponseEntity<>(userCart, HttpStatus.OK);
	    }

	    @PostMapping("/add-item/{userId}")
	    public ResponseEntity<CartItem> addCartItem(@PathVariable Long userId, @RequestBody AddItemRequest req) {
	        try {
	            CartItem addedItem = cartService.addCartItem(userId, req);
	            return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
	        } catch (ProductException e) {
	            // Handle product exception, return appropriate HTTP status and message
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
}
