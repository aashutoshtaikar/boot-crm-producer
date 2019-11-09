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

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		Optional<Customer> customerOptional = customerRepo.findById(id);
		if (!customerOptional.isPresent()) {
			throw new RuntimeException("customer not found with id " + id);
		}
		return customerOptional.get();
	}
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		Customer customerCreated = customerRepo.save(customer);
		return new ResponseEntity<Customer>(customerCreated, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable int id) {
		customerRepo.deleteById(id);
	}
	
	@GetMapping("searchName/{name}")
	public List<Customer> getCustomer(@PathVariable String name) throws Exception {
		Optional<List<Customer>> customerOptional = customerRepo.findByName(name);
		if (!customerOptional.isPresent()) {
			throw new RuntimeException("customer not found with name " + name);
		}
		return customerOptional.get();
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void CustomerNotFoundExceptionHandler(RuntimeException e) {
		// TODO impl
	}
}
