package com.campersDen.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campersDen.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

}
