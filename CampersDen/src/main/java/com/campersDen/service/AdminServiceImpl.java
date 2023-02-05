package com.campersDen.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.AdminException;
import com.campersDen.model.Admin;
import com.campersDen.model.AdminDTO;
import com.campersDen.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo aRepo;
	
	@Override
	public Admin addAdmin(AdminDTO adminDto) throws AdminException {
		
		if(aRepo.findByEmail(adminDto.getEmail()) != null)
			throw new AdminException("Admin already present with the email:-"+adminDto.getEmail());
		
		Admin admin = new Admin();
		
		admin.setEmail(adminDto.getEmail());
		admin.setPassword(adminDto.getPassword());
		admin.setName(adminDto.getName());
		
		return aRepo.save(admin);
		
	}

	@Override
	public Admin getAdminById(int adminId) throws AdminException {
		return aRepo.findById(adminId).orElseThrow(()-> new AdminException("No Admin found with that Id:-"+adminId));
	}

//	@Override
//	public Admin deleteAdminById(int adminId) throws AdminException {
//		Admin admin = aRepo.findById(adminId).orElseThrow(()-> new AdminException("No Admin found with that Id:-"+adminId));
//		aRepo.delete(admin);
//		return admin;
//	}
//
//	@Override
//	public Admin updateAdmin(Admin admin) throws AdminException {
//		Admin existingAdmin = aRepo.findById(admin.getAdminId()).orElseThrow(()-> new AdminException("No Admin found with that Id:-"+admin.getAdminId()));
//		return aRepo.save(admin);
//	}

}
