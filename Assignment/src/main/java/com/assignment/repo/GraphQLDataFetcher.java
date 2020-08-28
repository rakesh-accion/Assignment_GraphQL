package com.assignment.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GraphQLDataFetcher {
	@Autowired
	private UserRepo userRepo;

	public graphql.schema.DataFetcher getUserByIdDataFetcher() {
		return dataFetchingEnvironment -> {
			int userId = dataFetchingEnvironment.getArgument("id");
			return userRepo.findById(userId).orElse(null);
		};
	}
	
	public graphql.schema.DataFetcher getAllUsersDataFetcher() {
		return dataFetchingEnvironment -> {
			return userRepo.findAll();
		};
	}
}
