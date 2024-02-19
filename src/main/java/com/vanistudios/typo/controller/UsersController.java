package com.vanistudios.typo.controller;

import java.util.List;
import java.util.Optional;

import com.vanistudios.typo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanistudios.typo.repository.UserRepository;
import com.vanistudios.typo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private UserService userService;

	@Operation(summary = "create a user by providing user details.", method = "GET")
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@Operation(summary = "getUser by providing userId.")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.findUser(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		Optional<User> currentUser = usersRepository.findById(id);
		if (currentUser.isPresent()) {
			User updatedUser = currentUser.get();
			updatedUser.setUsername(user.getUsername());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setPassword(user.getPassword());
			updatedUser.setCreatedAt(user.getCreatedAt());
			updatedUser.setUpdatedAt(user.getUpdatedAt());
			usersRepository.save(updatedUser);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		Optional<User> user = usersRepository.findById(id);
		if (user.isPresent()) {
			usersRepository.delete(user.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAll")
	@Operation(summary = "get a list of all users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = (List<User>) usersRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/test")
	@Operation(summary = "test endpoint")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("TEST-TYPO-SUCCESS", HttpStatus.OK);
	}
}
