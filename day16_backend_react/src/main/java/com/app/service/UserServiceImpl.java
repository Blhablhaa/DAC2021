package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
