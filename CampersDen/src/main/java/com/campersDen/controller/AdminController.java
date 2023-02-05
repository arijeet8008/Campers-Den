package com.campersDen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.AdminException;
import com.campersDen.exception.SessionException;
import com.campersDen.model.Admin;
import com.campersDen.model.AdminDTO;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.service.AdminService;
import com.campersDen.service.SessionService;
//import com.campersDen.service.ISessionService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdmin(@RequestBody @Valid AdminDTO admin) throws AdminException{
		Admin savedAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
	}
	
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Integer adminId, @RequestParam("sessionKey") String sessionKey) throws AdminException, SessionException{
		Session session = sessionService.getASessionByKey(sessionKey);
		if(session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
			Admin admin = adminService.getAdminById(adminId);
			return new ResponseEntity<>(admin, HttpStatus.CREATED);
		}
		throw new SessionException("Please login with the correct credentials");
	}
	
}
