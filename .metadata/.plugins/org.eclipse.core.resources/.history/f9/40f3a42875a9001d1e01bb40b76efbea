package com.campersDen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.PaymentException;
import com.campersDen.model.Payment;
import com.campersDen.repo.PaymentRepo;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepo repo;

	@Override
	public Payment finalPayment(Payment payment) throws PaymentException {

		Payment p = repo.save(payment);
		
		if(p == null) {
			throw new PaymentException("Add payment to the Order first...");
		}
		
		return p;
		
	}

}
