package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.CustomerRepo;
import com.demo.domain.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping
	public Flux<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Customer> getCustomer(@PathVariable Integer id) {
		Mono<Customer> customerOptional = customerRepo.findById(id);

		return customerOptional;
	}
	
	@PostMapping
	public Mono<Customer> addCustomer(@RequestBody Customer customer){
		Mono<Customer> customerCreated = customerRepo.save(customer);
		return customerCreated;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable int id) {
		customerRepo.deleteById(id);
	}
	
	@GetMapping("searchName/{name}")
	public Flux<Customer> getCustomer(@PathVariable String name) throws Exception {
		Flux<Customer> customerOptional = customerRepo.findByName(name);

		return customerOptional;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void CustomerNotFoundExceptionHandler(RuntimeException e) {
		// TODO impl
	}
}
