package com.assignment.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

	public List<User> findAll();

	
}
