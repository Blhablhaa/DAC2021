package com.app.dao;

import java.util.List;

import com.app.pojos.User;

public interface IUserDao {
	//add a method for user login
	User validateUser(String email,String password);
   //list a method for listing vendor
	List<User> getAllVendors();
	//delet vendor details
	String deleteVendorDetails(User vendor);
	//get vendor details from its PK
	User findByUserId(int userId);
	//register new vendor
		String registerVendor(User vendor);
	
		
}
