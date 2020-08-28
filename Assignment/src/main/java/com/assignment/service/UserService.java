package com.assignment.service;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.assignment.repo.GraphQLDataFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import org.springframework.core.io.Resource;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.GraphQL;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class UserService {

	@Value("classpath:user.graphqls")
	Resource resource;

	private GraphQL graphQL;
	
	@Autowired
	private GraphQLDataFetcher graphQLDataFetcher;
	
	@PostConstruct
	public void init() throws IOException {
		URL url = Resources.getResource("user.graphqls");
		String sdl = Resources.toString(url, Charsets.UTF_8);
		GraphQLSchema graphQLSchema = buildSchema(sdl);
		this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}

	private GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}

	public RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",
						typeWriting -> typeWriting.dataFetcher("userById", graphQLDataFetcher.getUserByIdDataFetcher()))
				.type("Query",
						typeWriting -> typeWriting.dataFetcher("allUser", graphQLDataFetcher.getAllUsersDataFetcher()))
				.build();
	}
}
