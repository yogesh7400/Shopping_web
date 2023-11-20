package com.abm.service;

import com.abm.entity.Cart;
import com.abm.entity.CartItem;
import com.abm.entity.User;
import com.abm.exception.ProductException;
import com.abm.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);



}
