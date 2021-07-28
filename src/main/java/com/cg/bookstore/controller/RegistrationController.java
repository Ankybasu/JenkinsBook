package com.cg.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.service.CustomerServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;

@Api(value="Registration of customer",description="Operations pertaining to customer registration")
@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private CustomerServiceImpl customerService;
	
	@ApiOperation(value="add customer details",notes="To create an account by the customer",
			response=Contact.class)
	@PostMapping("/addcustomer")
	public ResponseEntity<String> addCust(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	@ApiOperation(value="view details",notes="To view details of the account by the customer",
			response=Contact.class)
	@GetMapping("/viewdetails/{id}")
	public ResponseEntity<Customer> viewDetails(@PathVariable("id") int id) {
		return customerService.viewCustomerById(id);
	}
	
}
