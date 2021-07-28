package com.cg.bookstore.service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Login;
import com.cg.bookstore.exceptions.UserNotPresentException;
import com.cg.bookstore.repository.ILoginRepository;

@Service
public class LoginServiceImpl implements ILoginService {
	@Autowired 
	private ILoginRepository loginServiceRepo;

	
//	@Override
//	public ResponseEntity<String> validateLogin(Login user1) {
//		// TODO Auto-generated method stub
//		Optional<Login> findByEmail = loginServiceRepo.findByEmailAndPassword(user1.getEmail(),user1.getPassword());
//		if (findByEmail.isPresent()) {
//			return new ResponseEntity<>("User Valid",HttpStatus.OK);
//		} else
//			return new ResponseEntity<>("User not present",HttpStatus.NOT_FOUND);
//	}



	@Override
	public ResponseEntity<String> validateLogin(String email, String password) {
//		// TODO Auto-generated method stub
		//System.out.println(email);
		Login user = loginServiceRepo.findByEmail(email);
		//Optional<Login> user = loginServiceRepo.findByEmailAndPassword(email,password);
		if (user!=null) {
			if(user.getPassword().equals(password))
			return new ResponseEntity<>("User Valid",HttpStatus.OK);
			else {
				return new ResponseEntity<>("password Invalid",HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<>("User not present",HttpStatus.NOT_FOUND);
	}

}