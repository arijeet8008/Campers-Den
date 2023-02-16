package com.campersDen.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campersDen.model.Category;
import com.campersDen.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer>{

	public List<Products> findByCategory(Category category);
	
}
