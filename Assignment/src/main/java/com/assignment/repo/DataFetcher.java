package com.assignment.repo;

import org.springframework.stereotype.Repository;

import graphql.schema.DataFetchingEnvironment;

@Repository
public interface DataFetcher<T> {
    T get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception;
}