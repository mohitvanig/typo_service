package com.vanistudios.typo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vanistudios.typo.entity.User;

@Service
public interface UserService {

	User createUser(String emailId, String username, String password);

	User createUser(User user);

	Optional<User> findUser(Long userId);

}
