package com.campersDen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.CustomerException;
import com.campersDen.exception.OrdersException;
import com.campersDen.exception.PaymentException;
import com.campersDen.model.Payment;
import com.campersDen.repo.PaymentRepo;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepo repo;

	@Override
	public Payment finalPayment(Payment payment, Integer orderId, Integer customerId)
			throws PaymentException, CustomerException, OrdersException {
		
		Payment p = repo.save(payment);
		
		if(p == null) {
			throw new PaymentException("Add payment to the Order first...");
		}
		
		return p;
	}


}
