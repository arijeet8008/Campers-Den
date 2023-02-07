package com.campersDen.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campersDen.exception.AdminException;
import com.campersDen.exception.CustomerException;
import com.campersDen.exception.LoginException;
import com.campersDen.model.Admin;
import com.campersDen.model.Customer;
import com.campersDen.model.LoginDTO;
import com.campersDen.model.Session;
import com.campersDen.model.UserType;
import com.campersDen.repo.AdminRepo;
import com.campersDen.repo.CustomerRepo;
import com.campersDen.repo.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SessionRepo sRepo;

	@Autowired
	private AdminRepo aRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Override
	public Session login(LoginDTO loginDto) throws LoginException, AdminException, CustomerException {
		Session session = null;

		if (loginDto.getUserType() == UserType.ADMIN) {

			Admin admin = aRepo.findByEmail(loginDto.getEmail());

			if (admin == null)
				throw new AdminException("No Admin found with that email:-" + loginDto.getEmail());

			Integer userId = admin.getAdminId();

			Optional<Session> sessionOpt = sRepo.findById(userId);

			if (sessionOpt.isPresent())
				throw new LoginException("User is already logged in. Please logout first and then try to login");

			if (!admin.getPassword().equals(loginDto.getPassword()))
				throw new LoginException("The email and password combination seems incorrect. Please try again");

			String key = RandomString.make(6);

			Session adminSession = new Session();
			adminSession.setSessionKey(key);
			adminSession.setTimeStamp(LocalDateTime.now());
			adminSession.setUserId(admin.getAdminId());
			adminSession.setUserType(UserType.ADMIN);
			session = sRepo.save(adminSession);

		}
		
		else if (loginDto.getUserType() == UserType.CUSTOMER) {

			Customer customer = cRepo.findByEmail(loginDto.getEmail());

			if (customer == null)
				throw new CustomerException("No Customer found with that email:-" + loginDto.getEmail());

			Integer userId = customer.getCustomerId();

			Optional<Session> sessionOpt = sRepo.findById(userId);

			if (sessionOpt.isPresent())
				throw new LoginException("User is already logged in. Please logout first and then try to login");

			if (!customer.getPassword().equals(loginDto.getPassword()))
				throw new LoginException("The email and password combination seems incorrect. Please try again");

			String key = RandomString.make(6);

			Session customerSession = new Session();
			customerSession.setSessionKey(key);
			customerSession.setTimeStamp(LocalDateTime.now());
			customerSession.setUserId(customer.getCustomerId());
			customerSession.setUserType(UserType.CUSTOMER);
			session = sRepo.save(customerSession);

		}

		return session;
	}

	@Override
	public String logout(Integer userId) throws LoginException {

		Session session = sRepo.findById(userId)
				.orElseThrow(() -> new LoginException("No user is logged in with that userId:-" + userId));
		sRepo.delete(session);
		return "User logged out successfully";
	}

}
