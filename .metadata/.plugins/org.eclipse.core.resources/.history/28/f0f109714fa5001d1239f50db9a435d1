package com.campersDen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.model.Products;
import com.campersDen.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Products> addProductsHandler(@RequestBody @Valid Products products){
		
		return new ResponseEntity<Products>(productService.addProducts(products), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Products>> viewAllProductsHandler(){
		
		List<Products> allProducts = productService.viewAllProducts();
		
		return new ResponseEntity<List<Products>>(allProducts, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Products> viewProductByIdHandler(@PathVariable Integer id){
		
		Products p = productService.findProductById(id);
		
		return new ResponseEntity<Products>(p, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/products")
	public ResponseEntity<Products> updateProductsHandler(@RequestBody Products products){
		
		Products p = productService.updateProducts(products);
		
		return new ResponseEntity<Products>(p, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Products> deleteProductHandler(@PathVariable Integer id){
		
		Products deleteProduct = productService.deleteProduct(id);
		
		return new ResponseEntity<Products>(deleteProduct, HttpStatus.ACCEPTED);
		
	}
	
}
