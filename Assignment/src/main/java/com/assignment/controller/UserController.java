package com.assignment.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Address;
import com.assignment.model.User;
import com.assignment.service.AllUserDataFetcher;
import com.assignment.service.UserService;

import graphql.ExecutionResult;
import graphql.schema.idl.RuntimeWiring;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private AllUserDataFetcher userService;

	@GetMapping("/getAllUser")
	public List<User> getAllUser() {
		return userService.get();
	}

	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/getAddressById/{id}")
	public Set<Address> getAddressById(@PathVariable int id) {
		return userService.getAddressById(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable int id) throws Exception {
		int deletedId = userService.deleteUser(id);
		return new ResponseEntity<>(deletedId, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> deleteUserById(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) throws Exception {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	@PostMapping("/getUser")
	public ResponseEntity<Object> getUserConfigs(@RequestBody String empReqst) {
		ExecutionResult execute = service.getGraphQL().execute(empReqst);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	
	// URL for graph query
	@GetMapping("/graphQuery")
	public RuntimeWiring getGraph() {
		return service.buildWiring();
	}
	
}
