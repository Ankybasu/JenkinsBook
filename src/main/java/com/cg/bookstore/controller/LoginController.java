package com.cg.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Login;
import com.cg.bookstore.exceptions.CustomerNotFoundException;
import com.cg.bookstore.service.ICustomerService;
import com.cg.bookstore.service.ILoginService;

@RestController
@RequestMapping("/customer")
public class LoginController {
	@Autowired
	private ILoginService loginService;
	
	@GetMapping("/login/{email}/{password}")
	//@ExceptionHandler(CustomerNotFoundException.class)
	private ResponseEntity<String> checkLogin(@Param("email") String email,@Param("password")  String password) {
		//String email=e.replace("%40", "@");
		//System.out.println(e);
		return loginService.validateLogin(email,password);
		
	}
}
