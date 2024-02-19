package com.vanistudios.typo.repository;

import org.springframework.data.repository.CrudRepository;

import com.vanistudios.typo.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	
}
