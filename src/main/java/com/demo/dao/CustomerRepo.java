package com.demo.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.demo.domain.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepo extends R2dbcRepository<Customer, Integer> {
	Flux<Customer> findByName(String name);
}
