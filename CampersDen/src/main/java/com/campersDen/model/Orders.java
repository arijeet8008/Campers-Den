package com.campersDen.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;

	private LocalDateTime orderDate;

//	@NotNull(message = "MAndatory")
//	private String transactionMode;

//	@NotNull(message = "MAndatory")
	private Integer quantity;

//	@NotNull(message = "MAndatory")
	private double totalCost;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToOne
	@JsonIgnore
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "deliveryAddressId")
	private DeliveryAddress deliveryAddress;
	
}
