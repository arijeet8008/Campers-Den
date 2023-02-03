package com.campersDen.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campersDen.model.Session;

@Repository
public interface SessionRepo extends JpaRepository<Session, Integer>{
	
	public Session findBySessionKey(String key);
}
