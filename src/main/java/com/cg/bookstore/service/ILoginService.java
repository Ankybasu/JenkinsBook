package com.cg.bookstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Login;

@Service
public interface ILoginService {
	//public ResponseEntity<String> validateLogin(Login user1);

	//public ResponseEntity<String> validateLogin(String email, String pass);

	//public ResponseEntity<String> validateLogin(Login login);

	public ResponseEntity<String> validateLogin(String email, String password);
}