package com.campersDen.service;

import com.campersDen.exception.CustomerException;
import com.campersDen.exception.ProductsException;
import com.campersDen.model.Cart;

public interface CartService {

	public Cart addProductsToCart(Integer productId,Integer customerId) throws ProductsException,CustomerException;
	
}
