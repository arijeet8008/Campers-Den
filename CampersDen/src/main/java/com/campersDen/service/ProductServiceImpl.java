package com.campersDen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.ProductsException;
import com.campersDen.model.Products;
import com.campersDen.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Products addProducts(Products products) throws ProductsException {
		
		Products saveProducts = productRepo.save(products);
		
		if(saveProducts == null)
			throw new ProductsException("Add Valid Product...");
		
		return saveProducts;
		
	}

	@Override
	public List<Products> viewAllProducts() throws ProductsException {
		
		List<Products> allProducts = productRepo.findAll();
		
		if(allProducts.isEmpty())
			throw new ProductsException("No Products Found");
		
		return allProducts;
		
	}

	@Override
	public Products findProductById(Integer id) throws ProductsException {
		
		return productRepo.findById(id).orElseThrow(() -> new ProductsException("Product Not Found"));
		
	}

	@Override
	public Products updateProducts(Products products) throws ProductsException {
		
		Optional<Products> opt = productRepo.findById(products.getPid());
		
		if(opt.isEmpty())
			throw new ProductsException("No Products Found");
		
		return productRepo.save(products);
		
	}

	@Override
	public Products deleteProduct(Integer id) throws ProductsException {
		
		Optional<Products> opt = productRepo.findById(id); 
		
		if(opt.isPresent()) {
			
			Products p = opt.get();
			productRepo.delete(p);
			return p;
			
		}
					
		else
			throw new ProductsException("No Products Found");
		
	}

}
