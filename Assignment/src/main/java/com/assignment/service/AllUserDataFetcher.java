package com.assignment.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.assignment.model.Address;
import com.assignment.model.Phone;
import com.assignment.model.User;
import com.assignment.repo.AddressRepo;
import com.assignment.repo.PhoneRepo;
import com.assignment.repo.UserRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllUserDataFetcher implements DataFetcher<User> {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AddressRepo addRepo;
	
	@Autowired
	private PhoneRepo phoneRepo;

	@Cacheable("users")
	public List<User> get() {
		return repo.findAll();
	}

	@Cacheable("user")
	public User getUserById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public Set<Address> getAddressById(int id) {
		User user= repo.findById(id).orElse(null);
		Set<Address> address= user.getAddress();
		return address;
	}

	@Override
	public User get(DataFetchingEnvironment dataFetchingEnvironment) {
		int id = dataFetchingEnvironment.getArgument("id");
		return repo.findById(id).orElse(null);
	}

	@CacheEvict("user")
	public int deleteUser(int id) throws Exception {
		User user = getUserById(id);
		if (user != null) {
			repo.deleteById(id);
			return user.getUserId();
		}
		throw new Exception("User not found");
	}

	@CacheEvict("user")
	public User updateUser(User update) {
		return repo.save(update);
	}

	@CacheEvict("user")
	public User createUser(User user) throws Exception {
		if (user == null) {
			throw new Exception("User is Empty");
		} else {
			return repo.save(user);
		}
	}
	
	public Address createAddress(Address address) throws Exception {
		if (address == null) {
			throw new Exception("address is Empty");
		} else {
			return addRepo.save(address);
		}
	}
	
	public Phone createPhone(Phone phone) throws Exception {
		if (phone == null) {
			throw new Exception("address is Empty");
		} else {
			return phoneRepo.save(phone);
		}
	}
	
	

}
