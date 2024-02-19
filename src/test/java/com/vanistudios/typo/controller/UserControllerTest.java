package com.vanistudios.typo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.vanistudios.typo.entity.User;
import com.vanistudios.typo.service.UserService;
import com.vanistudios.typo.serviceImpl.UserServiceImpl;

public class UserControllerTest {
	
	
	@Autowired
	private UserService userService;  
	
	
	@Test
	public void test() {
		
		userService = new UserServiceImpl();
		User user = new User();
		User newUser = userService.createUser(user);
		assertEquals(user, newUser);
		
	}
	

}
