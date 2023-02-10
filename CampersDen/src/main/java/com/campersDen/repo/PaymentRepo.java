package com.campersDen.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campersDen.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
