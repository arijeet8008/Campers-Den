package com.campersDen.service;

import com.campersDen.exception.SessionException;
import com.campersDen.model.Session;

public interface SessionService {

	public Session getASessionByKey(String key)throws SessionException;
}
