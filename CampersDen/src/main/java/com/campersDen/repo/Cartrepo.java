package com.campersDen.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campersDen.model.Cart;

@Repository
public interface Cartrepo extends JpaRepository<Cart, Integer>{

}
