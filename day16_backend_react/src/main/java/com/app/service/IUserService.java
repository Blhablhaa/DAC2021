package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {
//add a method to list all users
	List<User> fetchAllUsers();
}
