package com.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	Optional<List<Customer>> findByName(String name);
}
