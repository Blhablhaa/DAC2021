package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.pojos.User;

@SpringBootTest
class UserServiceTests {
	@Autowired
	private IUserService userService;

	@Test
	public void testFetchAllUsers()
	{
		List<User> users = userService.fetchAllUsers();
		System.out.println(users);
		assertEquals(3,users.size());
	}

}
