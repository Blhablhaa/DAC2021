package com.app.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.app.pojos.Address;
import com.app.pojos.User;

@SpringBootTest
class UserDaoTests {
	@Autowired
	private UserRepository userRepo;

	@Test
	void testAddUser() {
		
		List<User> users=Arrays.asList(new User("Rama", "1234", "Rama", "Seth", 30,12345),
				new User("Kiran", "2234", "Kiran", "Kher", 32,22345),
				new User("Sameer", "3234", "Sameer", "Singh", 35,32345));
		userRepo.saveAll(users);
		
	}
	@Test
	void linkUserAddress()
	{
		Address a1=new Address("mumbai", "MH", "India");
		User user=userRepo.findById(6).get();
		System.out.println(user);
		user.setAdr(a1);
		userRepo.save(user);
		
	}

}
