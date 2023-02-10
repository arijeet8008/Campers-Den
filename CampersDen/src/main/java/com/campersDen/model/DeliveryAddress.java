package com.campersDen.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class DeliveryAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@NotNull
	@NotBlank
	private String houseNo;
	
	@NotNull
	@NotBlank
	private String colony;
	
	@NotNull
	@NotBlank
	private String city;
	
	@NotNull
	@NotBlank
	private String state;
	
	@NotNull
	private Integer pinCode;
	
//	@JsonIgnore
//	@OneToOne(mappedBy = "deliveryAddress")
//	private Orders orders;
	
}
