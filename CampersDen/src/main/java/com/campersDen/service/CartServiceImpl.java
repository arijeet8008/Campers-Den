package com.campersDen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.CartException;
import com.campersDen.exception.CustomerException;
import com.campersDen.exception.ProductsException;
import com.campersDen.model.Cart;
import com.campersDen.model.Customer;
import com.campersDen.model.Products;
import com.campersDen.repo.Cartrepo;
import com.campersDen.repo.ProductRepo;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private Cartrepo cartrepo;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductRepo productRepo;
	
//	@Autowired
//	private ProductService productService;

	@Override
	public Cart addProductsToCart(Integer productId, Integer customerId) throws CartException,
									ProductsException, CustomerException {

		Customer customer = customerService.getCustomerById(customerId);
		
		if(customer != null) {
			
			Optional<Products> opt = productRepo.findById(productId);
			
			if(opt.isPresent()) {
				
				Cart cart = customer.getCart();
				
				cart.getProducts().add(opt.get());
				
//				List<Products> list = productService.viewAllProducts();
//				list.add(opt.get());
				
//				Double totalAmount = 0.0;
				
//				for (Products products : list) {
//					totalAmount += products.getPrice();
//				}
//				cart.setTotalAmount(totalAmount);
//				cart.setQuantity(list.size());
				
				return cartrepo.save(cart);
				
			}
			
			else {
				throw new ProductsException("No Products Found By Id:"+productId);
			}
			
		}
		
		else {
			throw new CartException("No Customer Found By id:"+customerId);
		}
		
	}

	@Override
	public Cart viewCartbyId(Integer cartId, Integer customerId) throws CartException, CustomerException {

		Customer customer = customerService.getCustomerById(customerId);

		if (customer != null) {

			return cartrepo.findById(cartId).orElseThrow(() -> new CartException());

		} else {
			throw new CartException("No customer found by id:" + customerId);
		}

	}
	
}
