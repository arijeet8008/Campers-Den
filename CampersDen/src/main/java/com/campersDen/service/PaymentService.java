package com.campersDen.service;

import com.campersDen.exception.CustomerException;
import com.campersDen.exception.OrdersException;
import com.campersDen.exception.PaymentException;
import com.campersDen.model.Payment;

public interface PaymentService {

	public Payment finalPayment(Payment payment,Integer orderId,Integer customerId) throws PaymentException,CustomerException,OrdersException;
	
}
