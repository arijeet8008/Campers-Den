package com.campersDen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.CartException;
import com.campersDen.exception.CustomerException;
import com.campersDen.exception.SessionException;
import com.campersDen.model.Cart;
import com.campersDen.model.Products;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.service.CartService;
import com.campersDen.service.SessionService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping("/products/{productId}")
	public ResponseEntity<Cart> addProductsToCartHandler(@RequestParam("customerId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey, @PathVariable("productId") Integer productId) throws SessionException, CustomerException{
		
		Session session = sessionService.getASessionByKey(sessionKey);
		
		if(session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {
			
			Cart updateCart = cartService.addProductsToCart(productId, customerId);
			
			Double totalPrice = 0.0;
			
			List<Products> allProducts = updateCart.getProducts();
			
			for (Products products : allProducts) {
				totalPrice += products.getPrice();
			}
			
			updateCart.setTotalAmount(totalPrice);
			
			updateCart.setQuantity(allProducts.size());
			
			return new ResponseEntity<Cart>(updateCart, HttpStatus.ACCEPTED);
			
		}
		else {
			throw new SessionException("Please login with the correct credentials");
		}
		
	}
	
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<Cart> viewCartByIdHandler(@PathVariable("cartId") Integer cartId,
			@RequestParam("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey)
			throws CartException, CustomerException, SessionException {
		Session session = sessionService.getASessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.viewCartbyId(cartId, customerId);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}
	}
	
}
