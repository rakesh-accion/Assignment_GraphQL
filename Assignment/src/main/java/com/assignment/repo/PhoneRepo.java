
package com.assignment.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Phone;

@Repository
public interface PhoneRepo extends CrudRepository<Phone, Integer> {

	
}