package com.vanistudios.typo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanistudios.typo.entity.User;
import com.vanistudios.typo.repository.UserRepository;
import com.vanistudios.typo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(String emailId ,String username, String password) {
		User user = new User();
//		user.setEmail(emailId);
//		user.setPassword(password);
//		user.setUsername(username);
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findUser(Long userId) {
		Optional<User> user =  userRepository.findById(userId);
		return user;
	}

}
