package com.campersDen.service;

import java.util.List;

import com.campersDen.exception.ProductsException;
import com.campersDen.model.Products;

public interface ProductService {

	public Products addProducts(Products products) throws ProductsException;
	
	public List<Products> viewAllProducts() throws ProductsException;
	
	public Products findProductById(Integer id) throws ProductsException;
	
	public Products updateProducts(Products products) throws ProductsException;
	
	public Products deleteProduct(Integer id) throws ProductsException;
		
}
