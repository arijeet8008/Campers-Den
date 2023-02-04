package com.campersDen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.campersDen.exception.AdminException;
import com.campersDen.model.Admin;
import com.campersDen.model.AdminDTO;
import com.campersDen.service.IAdminService;
import com.campersDen.service.ISessionService;

@RestController
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
//	@Autowired
//	private ISessionService sessionService;
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdmin(@RequestBody @Valid AdminDTO admin) throws AdminException{
		Admin savedAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
	}
	
}
