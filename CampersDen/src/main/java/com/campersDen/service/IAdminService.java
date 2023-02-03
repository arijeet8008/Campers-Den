package com.campersDen.service;

import com.campersDen.exception.AdminException;
import com.campersDen.model.Admin;
import com.campersDen.model.AdminDTO;

public interface IAdminService {

	public Admin addAdmin(AdminDTO adminDto)throws AdminException;
	
//	public Admin getAdminById(int adminID)throws AdminException;
//	
//	public Admin deleteAdminById(int adminID)throws AdminException;
//	
//	public Admin updateAdmin(Admin admin)throws AdminException;
}
