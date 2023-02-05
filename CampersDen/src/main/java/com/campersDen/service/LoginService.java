package com.campersDen.service;


import com.campersDen.exception.AdminException;
import com.campersDen.exception.CustomerException;
import com.campersDen.exception.LoginException;
import com.campersDen.model.LoginDTO;
import com.campersDen.model.Session;

public interface LoginService {

	public Session login(LoginDTO loginDto)throws LoginException, AdminException,CustomerException;
	
	public String logout(Integer userId)throws LoginException;
}
