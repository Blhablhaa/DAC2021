package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//add a finder method to fetch user details by city 
	List<User> findByAdrCity(String city);
	//add a custom query method to get all user details with age > specified age
	@Query("select u.fName from User u where u.age > :age123")
	List<String> displayUserNamesByAge(@Param("age123")int age);
	
}
