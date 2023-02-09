package com.campersDen.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campersDen.model.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer>{

}
