package com.cg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Login;
import com.cg.bookstore.exceptions.CustomerAlreadyPresentException;
import com.cg.bookstore.exceptions.CustomerNotFoundException;
import com.cg.bookstore.service.ICustomerService;
import com.cg.bookstore.service.ILoginService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired (required = true)
	private ICustomerService customerService;
	@Autowired
	private ILoginService loginService;
	
	@PostMapping("/createcustomer")
    @ExceptionHandler(CustomerAlreadyPresentException.class)
    ResponseEntity<String> createCustomer(@RequestBody Customer c) {  
        // persisting the book
    	return customerService.createCustomer(c);
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok("Customer "+id+" is deleted");
	}
	
	@GetMapping("/viewcustomer/{id}")
	@ExceptionHandler(CustomerNotFoundException.class)
	public Customer viewCustomer(@PathVariable("id")int id) {
		return customerService.viewCustomer(id);
	}
	
	@GetMapping("/viewallcustomers")
	@ExceptionHandler(CustomerNotFoundException.class)
	public List<Customer> viewAllCustomer() {
		return customerService.listCustomers();
	}
	
	@PutMapping("/updatecustomer")
	@ExceptionHandler(CustomerNotFoundException.class)
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

}