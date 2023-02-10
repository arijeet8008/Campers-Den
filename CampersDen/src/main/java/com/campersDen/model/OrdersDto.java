package com.campersDen.model;

import javax.validation.constraints.NotNull;

public class OrdersDto {

	@NotNull
	private DeliveryAddressDTO deliveryAddressDTO;

	public DeliveryAddressDTO getDeliveryAddressDTO() {
		return deliveryAddressDTO;
	}

	public void setDeliveryAddressDTO(DeliveryAddressDTO deliveryAddressDTO) {
		this.deliveryAddressDTO = deliveryAddressDTO;
	}
	
	
	
}
