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
		
		User user =  userRepo.getById(userId);//.orElseThrow(() -> new UserHandlingException("Invalid User ID"));
	    user.getAge();
	    return user;
	}

	@Override
	public User updateUserDetails(User detachedUser) {
		// update user details
		return userRepo.save(detachedUser);
	}

	@Override
	public List<User> getUsersByCity(String city) {
		// TODO Auto-generated method stub
		return userRepo.findByAdrCity(city);
	}

	@Override
	public List<String> getUsersByAge(int age) {
		// TODO Auto-generated method stub
		return userRepo.displayUserNamesByAge(age);
	}

	
	
	

}
