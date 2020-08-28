
package com.assignment.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Address;

@Repository
public interface AddressRepo extends CrudRepository<Address, Integer> {

}

//@Query("select * from Address ad inner join User us where us.id=:id")
	//Address findAddressById(@Param("id") int id);