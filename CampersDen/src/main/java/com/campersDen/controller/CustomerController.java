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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.CustomerException;
import com.campersDen.exception.SessionException;
import com.campersDen.model.Customer;
import com.campersDen.model.CustomerDTO;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.service.CustomerService;
import com.campersDen.service.SessionService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody @Valid CustomerDTO customer) throws CustomerException{
		Customer addedCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);		
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey) throws CustomerException, SessionException{
		Session session = sessionService.getASessionByKey(sessionKey);
		if(session.getUserId() == customerId || session.getUserType() == UserType.ADMIN) {
			Customer customer = customerService.getCustomerById(customerId);
			return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		}
		throw new SessionException("Please login with the correct credentials");
		
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey) throws CustomerException, SessionException{
		Session session = sessionService.getASessionByKey(sessionKey);
		if(session.getUserId() == customerId || session.getUserType() == UserType.ADMIN) {
			Customer customer = customerService.deleteCustomerById(customerId);
			return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		}
		throw new SessionException("Please login with the correct credentials");
		
	}
	
	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer, @RequestParam("SessionKey")String sessionKey) throws CustomerException, SessionException{
		Session session = sessionService.getASessionByKey(sessionKey);
		if(session.getUserId() == customer.getCustomerId() || session.getUserType() == UserType.ADMIN) {
			Customer updatedCustomer = customerService.updateCustomer(customer);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
		}
		throw new SessionException("Please login with the correct credentials");
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam("sessionKey")String sessionKey) throws CustomerException, SessionException{
		Session session = sessionService.getASessionByKey(sessionKey);
		if(session.getUserType() == UserType.ADMIN) {
			return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.ACCEPTED);
		}
		throw new SessionException("Please login with the correct credentails");
	}
}

















