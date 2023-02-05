package com.campersDen.service;

import org.springframework.stereotype.Service;

import com.campersDen.exception.ProductsException;
import com.campersDen.model.Products;
import com.campersDen.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepo productRepo;

	@Override
	public Products addProducts(Products products) throws ProductsException {
		
		Products saveProducts = productRepo.save(products);
		
		if(saveProducts == null)
			throw new ProductsException("Add Valid Product...");
		
		return saveProducts;
		
	}

}
