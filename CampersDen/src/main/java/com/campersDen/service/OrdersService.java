package com.campersDen.service;

import java.util.List;

import com.campersDen.exception.OrdersException;
import com.campersDen.model.Orders;

public interface OrdersService {

	public Orders addOrder(Orders orders) throws OrdersException;
	
	public List<Orders> viewAllOrders()throws OrdersException;
	
	public Orders viewOrderById(Integer id)throws OrdersException;
	
	public Orders deleteOrdrerById(Integer id)throws OrdersException;
	
	public Orders updateOrder(Orders orders) throws OrdersException;
	
}
