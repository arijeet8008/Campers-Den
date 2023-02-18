package com.campersDen.model;

import java.net.URL;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
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
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pid;
	
	@NotNull(message = "M1")
	private URL images;
	
	@NotNull(message = "M2")
	private String name;
	
	@NotNull(message = "M3")
	@Min(value = 1,message = "price should not be Zero")
	private Double price;
	
	@Enumerated(value = EnumType.STRING)
	private Category category;
	
	
}
