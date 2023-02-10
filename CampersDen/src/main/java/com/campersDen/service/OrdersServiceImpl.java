package com.campersDen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.CartException;
import com.campersDen.exception.CustomerException;
import com.campersDen.exception.OrdersException;
import com.campersDen.model.DeliveryAddress;
import com.campersDen.model.Orders;
import com.campersDen.model.OrdersDto;
import com.campersDen.repo.OrdersRepo;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersRepo ordersRepo;

	@Override
	public Orders addOrder(Orders orders,Integer customerId,Integer cartId) throws 
	OrdersException,CartException,CustomerException {
		
//		Orders orders = new Orders();
		
//		DeliveryAddress deliveryAddress = new DeliveryAddress();
//		deliveryAddress.setCity(ordersDto.getDeliveryAddressDTO().getCity());
//		deliveryAddress.setColony(ordersDto.getDeliveryAddressDTO().getColony());
//		deliveryAddress.setHouseNo(ordersDto.getDeliveryAddressDTO().getHouseNo());
//		deliveryAddress.setPinCode(ordersDto.getDeliveryAddressDTO().getPinCode());
//		deliveryAddress.setState(ordersDto.getDeliveryAddressDTO().getState());
//		
//		orders.setDeliveryAddress(deliveryAddress);
		
		orders.setOrderDate(LocalDateTime.now());
		
		Orders saveOrder = ordersRepo.save(orders);
		
		if(saveOrder == null) {
			throw new OrdersException("Add product to the Order first...");
		}	

		else {
			return saveOrder;
		}
		
	}

	@Override
	public List<Orders> viewAllOrders() throws OrdersException {
		
		List<Orders> orders = ordersRepo.findAll();
		
		if(orders.isEmpty())
			throw new OrdersException("No Record Found...");
		
		return orders;
		
	}

	@Override
	public Orders viewOrderById(Integer id) throws OrdersException {

		return ordersRepo.findById(id).orElseThrow(()-> new OrdersException("Order Not found with id : "+id));

	}

	@Override
	public Orders deleteOrdrerById(Integer id) throws OrdersException {
		
		Optional<Orders> opt = ordersRepo.findById(id);
		
		if(opt.isPresent()) {
		
			Orders o = opt.get();
			ordersRepo.delete(o);
			return o;
			
		}
		else
			throw new OrdersException("Order Not found with id : "+id);
	
	}

	@Override
	public Orders updateOrder(Orders orders) throws OrdersException {
		
		Optional<Orders> opt = ordersRepo.findById(orders.getBookingOrderId());
		
		if(opt.isPresent()) {
		
			Orders o = ordersRepo.save(orders);

			return o;
			
		}
		else
			throw new OrdersException("Order Not found with id : "+orders.getBookingOrderId());
		
	}	
		
}
