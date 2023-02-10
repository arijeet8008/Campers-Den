package com.campersDen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.CustomerException;
import com.campersDen.exception.OrdersException;
import com.campersDen.exception.PaymentException;
import com.campersDen.exception.SessionException;
import com.campersDen.model.Orders;
import com.campersDen.model.Payment;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.service.OrdersService;
import com.campersDen.service.PaymentService;
import com.campersDen.service.SessionService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private SessionService sessionService;
	
//	@Autowired
//	private CustomerService customerService;
	
	@Autowired
	private OrdersService ordersService;
	
	@PostMapping(value = "/{customerId}/{sessionKey}/{orderId}")
	public ResponseEntity<Payment> createPaymentHandler(@RequestBody Payment payment,
			@PathVariable("customerId") Integer customerId, @PathVariable("sessionKey") String sessionKey,
			@PathVariable("orderId") Integer orderId) throws SessionException, PaymentException, OrdersException, CustomerException{
		
		Session session = sessionService.getASessionByKey(sessionKey);
		
		if(session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {
			
			Orders orders = ordersService.viewOrderById(orderId);
			
			payment.setTotalOrdervalue(orders.getTotalCost());
			payment.setOrders(orders);
			
			Payment p = paymentService.finalPayment(payment, orderId, customerId);
			
			return new ResponseEntity<Payment>(p, HttpStatus.CREATED);
			
		}
		
		else 
			throw new SessionException("Please login with the correct credentials");
		
	}
	
}
