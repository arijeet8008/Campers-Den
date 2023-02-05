package com.campersDen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.SessionException;
import com.campersDen.model.Session;
import com.campersDen.repo.SessionRepo;

@Service
public class SessionServiceImpl implements SessionService{

	@Autowired
	private SessionRepo sRepo;
	
	@Override
	public Session getASessionByKey(String key) throws SessionException {
		
		Session currentSession = sRepo.findBySessionKey(key);
		
		if(currentSession == null)
			throw new SessionException("No session fouund with that session key");
		
		return currentSession;
	}

}
