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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.CustomerException;
import com.campersDen.exception.SessionException;
import com.campersDen.model.Cart;
import com.campersDen.model.Orders;
import com.campersDen.model.Products;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.service.CartService;
import com.campersDen.service.CustomerService;
import com.campersDen.service.OrdersService;
import com.campersDen.service.SessionService;

@RestController
@RequestMapping(value = "orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	@PostMapping(value = "/{customerId}/{sessionKey}/{cartId}")
	public ResponseEntity<Orders> addOrdersHandler(@Valid @RequestBody Orders orders,
			@PathVariable("customerId") Integer customerId, @PathVariable("sessionKey") String sessionKey,
			@PathVariable("cartId") Integer cartId) throws SessionException, CustomerException {

		Session session = sessionService.getASessionByKey(sessionKey);
		
		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {
			
			Cart cart = cartService.viewCartbyId(cartId, customerId);
			orders.setCart(cart);
			orders.setCustomer(customerService.getCustomerById(customerId));

			List<Products> products = cart.getProducts();

			Double productCost = 0.0;
			
//			Integer totalQuantity = 0;

			for (Products p : products) {
				productCost = productCost + p.getPrice();
//				totalQuantity += p.getQuantity();
			}
			
			orders.setQuantity(products.size());

			orders.setTotalCost(productCost);
			
//			orders.getCart().setTotalAmount(productCost);

			Orders saveOrders = ordersService.addOrder(orders, customerId, cartId);

			return new ResponseEntity<Orders>(saveOrders, HttpStatus.CREATED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}
	}

	@GetMapping(value = "")
	public ResponseEntity<List<Orders>> viewAllOrdersHandler() {

		List<Orders> list = ordersService.viewAllOrders();

		return new ResponseEntity<List<Orders>>(list, HttpStatus.ACCEPTED);

	}

	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> viewOrderHandler(@PathVariable("id") Integer id) {

		Orders orders = ordersService.viewOrderById(id);

		return new ResponseEntity<Orders>(orders, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Orders> deleteOrderHandler(@PathVariable("id") Integer id,
			@RequestParam("adminId") Integer adminId, @RequestParam("sessionKey") String sessionKey)
			throws SessionException {

		Session session = sessionService.getASessionByKey(sessionKey);
		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {

			Orders orders = ordersService.deleteOrdrerById(id);

			return new ResponseEntity<Orders>(orders, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}

	}

	@PutMapping(value = "/{adminId}/{sessionKey}")
	public ResponseEntity<Orders> updateOrdersHandler(@RequestBody Orders orders,
			@PathVariable("adminId") Integer adminId, @PathVariable("sessionKey") String sessionKey)
			throws SessionException {

		Session session = sessionService.getASessionByKey(sessionKey);
		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {

			Orders saveOrders = ordersService.updateOrder(orders);

			return new ResponseEntity<Orders>(saveOrders, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}

	}

}
	

