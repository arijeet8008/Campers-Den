package com.campersDen.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.AdminException;
import com.campersDen.exception.CustomerException;
import com.campersDen.exception.LoginException;
import com.campersDen.model.LoginDTO;
import com.campersDen.model.Session;
import com.campersDen.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<Session> login(@RequestBody LoginDTO loginDto) throws LoginException, AdminException, CustomerException{
		Session session = loginService.login(loginDto);
		return new ResponseEntity<>(session, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout/{userId}")
	public ResponseEntity<String> logout(@PathVariable("userId") Integer userId)throws LoginException{
		String message = loginService.logout(userId);
		return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
	}
	
}
