package com.ncs.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.rest.entity.User;
import com.ncs.rest.exception.ResourceNotFoundException;
import com.ncs.rest.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping
	public List<User> getAllUsers(){
		
		return this.userRepository.findAll();
	}
	//get users by id
	@GetMapping("/{id}")
	public User findUserById(@PathVariable(value = "id" ) long userId) {
		
		return this.userRepository
.findById(userId).orElseThrow(() -> new ResourceNotFoundException("resource not found " +userId));	}
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		
		return this.userRepository.save(user);
		
	}
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable(value = "id") long userId) {
		
		User existingUser = this.userRepository
				.findById(userId).orElseThrow(() -> new ResourceNotFoundException("resource not found " +userId));	
		existingUser.setFirstName(user.getFirstName());	
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		return this.userRepository.save(existingUser);
	
	}
	
		
	
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId){
		User existingUser = this.userRepository
				.findById(userId).orElseThrow(() -> new ResourceNotFoundException("resource not found " +userId));	
       this.userRepository.delete(existingUser);	
      return ResponseEntity.ok().build();
		
	}
	
}


