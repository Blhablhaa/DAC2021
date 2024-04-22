package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dao.UserRepository;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	//dependency : dao layer i/f
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> fetchAllUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User saveUserDetails(User transientUser) {
		
		return userRepo.save(transientUser);
	}

	@Override
	public String deleteUserDetails(int userId) {
		userRepo.deleteById(userId);
		return "User details deleted";
	}

	@Override
	public User getUserDetails(int userId) {
		
		return userRepo.findById(userId).orElseThrow(() -> new UserHandlingException("Invalid User ID"));
	}

	@Override
	public User updateUserDetails(User detachedUser) {
		//update user detail
		return userRepo.save(detachedUser);
	}
	
	

}
