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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.SessionException;
import com.campersDen.model.Products;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.service.ProductService;
import com.campersDen.service.SessionService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping("/{adminId}/{sessionKey}")
	public ResponseEntity<Products> addProductsHandler(@RequestBody @Valid Products products,@PathVariable("adminId") Integer adminId,
			@PathVariable("sessionKey") String sessionKey) throws SessionException{
		
		Session session = sessionService.getASessionByKey(sessionKey);
		
		if(session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
			
			return new ResponseEntity<Products>(productService.addProducts(products), HttpStatus.CREATED);
			
		}
		
		else 
			throw new SessionException("Please login with the correct credentials");
			
	}
	
	@GetMapping("")
	public ResponseEntity<List<Products>> viewAllProductsHandler(){
		
		List<Products> allProducts = productService.viewAllProducts();
		
		return new ResponseEntity<List<Products>>(allProducts, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Products> viewProductByIdHandler(@PathVariable Integer id){
		
		Products p = productService.findProductById(id);
		
		return new ResponseEntity<Products>(p, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/{adminId}/{sessionKey}")
	public ResponseEntity<Products> updateProductsHandler(@RequestBody Products products,@PathVariable("adminId") Integer adminId,
			@PathVariable("sessionKey") String sessionKey) throws SessionException{
		
		Session session = sessionService.getASessionByKey(sessionKey);
		
		if(session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
			
			Products p = productService.updateProducts(products);
			return new ResponseEntity<Products>(p, HttpStatus.ACCEPTED);
		}
		
		else
			throw new SessionException("Please login with the correct credentials");
			
	}
	
	@DeleteMapping("/{adminId}/{sessionKey}/{id}")
	public ResponseEntity<Products> deleteProductHandler(@PathVariable Integer id,@PathVariable("adminId") Integer adminId,
			@PathVariable("sessionKey") String sessionKey) throws SessionException{
		
		Session session = sessionService.getASessionByKey(sessionKey);
		
		if(session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
			
				Products deleteProduct = productService.deleteProduct(id);
		
				return new ResponseEntity<Products>(deleteProduct, HttpStatus.ACCEPTED);
			
		}
		
		else
			throw new SessionException("Please login with the correct credentials");

		
	}
	
}
