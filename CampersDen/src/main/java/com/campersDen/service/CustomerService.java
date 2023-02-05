package com.campersDen.service;

import java.util.List;

import com.campersDen.exception.CustomerException;
import com.campersDen.model.Customer;
import com.campersDen.model.CustomerDTO;

public interface CustomerService {

	public Customer addCustomer(CustomerDTO customer)throws CustomerException;
	
	public Customer getCustomerById(Integer customerId)throws CustomerException;
	
	public Customer deleteCustomerById(Integer customerId)throws CustomerException;
	
	public Customer updateCustomer(Customer customer)throws CustomerException;
	
	public List<Customer> getAllCustomers()throws CustomerException;
}
