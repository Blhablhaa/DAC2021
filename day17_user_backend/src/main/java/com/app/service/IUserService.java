package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {
//add a method to list all users.
	List<User> fetchAllUsers();

	// add a method to save user details
	User saveUserDetails(User transientUser);

	// add a method to delete user details
	String deleteUserDetails(int userId);

	// add a method to fetch user details by id
	User getUserDetails(int userId);

	// add a method to update existing user details
	User updateUserDetails(User detachedUser);

	// add a method to list all users residing in a specific city
	List<User> getUsersByCity(String city);

	// add a method to list all user names with age > :age
	List<String> getUsersByAge(int age);

}
