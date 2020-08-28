package com.assignment;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.assignment.model.Address;
import com.assignment.model.Phone;
import com.assignment.model.User;
import com.assignment.service.AllUserDataFetcher;

@SpringBootApplication
@EnableCaching
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	ApplicationRunner init(AllUserDataFetcher repository) {
		return (ApplicationArguments args) -> dataSetup(repository);
	}

	protected static final String[] FIRST_NAME= {"Rakesh","Yuraj","Sehwag","Einstien","Liya"};
	protected static final String[] LAST_NAME= {"Sharma","Singh","Virender","Albert","RK"};
	protected static final String[] CITY_NAME= {"Banglore","London","Pitsberg","Tumkur"};
	
	
	public void dataSetup(AllUserDataFetcher repository) throws Exception {
		Random random=new Random();
		for (int i = 1; i < 2000; i++) {
			this.createRandommUser(i,repository,random);
		}
	}
	
	public void createRandommUser(int index, AllUserDataFetcher repository,Random random) throws Exception {
		User user = new User();
		user.setUserId(index);
		user.setFirstName(FIRST_NAME[random.nextInt(FIRST_NAME.length)]);
		user.setLastName(LAST_NAME[random.nextInt(LAST_NAME.length)]);

		repository.createUser(user);

		Address address = new Address();
		address.setStreetNo(index);
		address.setCity(CITY_NAME[random.nextInt(CITY_NAME.length)]);
		address.setStreetName("SGR");
		address.setUser(user);

		repository.createAddress(address);

		Phone phone = new Phone();
		phone.setId(index);
		phone.setPhoneNumber("1234567890");
		phone.setAddress(address);

		repository.createPhone(phone);
	}
}
