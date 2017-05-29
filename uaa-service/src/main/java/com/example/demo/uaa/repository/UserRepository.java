package com.example.demo.uaa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.uaa.domain.User;

/**
 * A CRUD repository for user storage and manipulation.
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Finds a user by name.
	 * 
	 * @param name the user name
	 * 
	 * @return a user if found
	 */
	Optional<User> findOneByName(String name);	
}