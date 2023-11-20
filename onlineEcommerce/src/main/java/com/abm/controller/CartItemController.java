package com.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.CartItem;
import com.abm.exception.CartItemException;
import com.abm.exception.UserException;
import com.abm.service.CartItemServiceImplementation;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
	
	 @Autowired
	    private CartItemServiceImplementation cartItemService;
	
	
	@PostMapping("/create")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.createCartItem(cartItem);
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long id,
            @RequestBody CartItem cartItem
    ) {
        try {
            CartItem updatedCartItem = cartItemService.updateCartItem(userId, id, cartItem);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (CartItemException | UserException e) {
            // Handle exceptions and return appropriate HTTP status and message
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{userId}/{cartItemId}")
    public ResponseEntity<String> removeCartItem(
            @PathVariable Long userId,
            @PathVariable Long cartItemId
    ) {
        try {
            cartItemService.removeCartItem(userId, cartItemId);
            return new ResponseEntity<>("Cart item removed successfully", HttpStatus.OK);
        } catch (CartItemException | UserException e) {
            // Handle exceptions and return appropriate HTTP status and message
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long cartItemId) {
        try {
            CartItem cartItem = cartItemService.findCartItemById(cartItemId);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } catch (CartItemException e) {
            // Handle exception and return appropriate HTTP status and message
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
