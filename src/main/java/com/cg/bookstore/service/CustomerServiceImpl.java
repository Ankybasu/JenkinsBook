package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Login;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryNotFoundException;
import com.cg.bookstore.exceptions.CustomerAlreadyPresentException;
import com.cg.bookstore.exceptions.CustomerNotFoundException;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.ILoginRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{


	@Autowired 
	private ICustomerRepository customerServiceRepo;
	
	@Autowired
	private IBookOrderRepository bookOrderRepo;
	@Autowired
	private ILoginRepository loginRepo;
	
	@Override
	public ResponseEntity<String> createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
		//Optional<Customer> findCustomerById = customerServiceRepo.findById(customer.getCustomerId());
		Optional<Customer> findCustomerByEmail = customerServiceRepo.findByEmail(customer.getEmail());
		if (!findCustomerByEmail.isPresent()) {
			if(customer.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
			customerServiceRepo.save(customer);
			loginRepo.save(new Login(customer.getEmail(),customer.getPassword()));
			
			}
			else {
				return new ResponseEntity<>("Invalid email",HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity<>("Account  already exists!",HttpStatus.NOT_FOUND);
		}
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<>("No account created yet",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Account registered!",HttpStatus.OK);
	}
	@Override
	public List<Customer> listCustomers() {
		// TODO Auto-generated method stub
		return customerServiceRepo.findAll();
	}

	@Override
	public String deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		Optional<Customer> findCustomerById = customerServiceRepo.findById(id);
		if (findCustomerById.isPresent()) {
			customerServiceRepo.deleteById(id);
			return "customer deleted";
		}
		else
		{
			throw new CustomerNotFoundException("Customer does not exist");
		}
	}

	@Override
	public Customer updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		Optional<Customer> findBookById = customerServiceRepo.findById(c.getCustomerId());
		if (findBookById.isPresent()) {
			customerServiceRepo.save(c);
			//Login existingUserId=loginRepo.findByEmail(c.getEmail());
			//loginRepo.save(new Login(existingUserId.getLoginId(),c.getEmail(),c.getPassword()));
			return c;
		} else
			throw new CustomerNotFoundException(
					"Customer with Id: " + c.getCustomerId() + " not exists!!");
	}

	@Override
	public ResponseEntity<Customer> viewCustomerById(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> findBookById = customerServiceRepo.findById(id);
		try {
		if (!findBookById.isPresent()) {
			throw new CustomerNotFoundException("Customer with id:"+findBookById.get()+" does not exist!");
		} 
		}
		catch(IllegalArgumentException e) {
			throw new CustomerNotFoundException("No customer exists!");
		}
		return new ResponseEntity<>(findBookById.get(),HttpStatus.OK);
	}

	@Override
	public List<Customer> listAllCustomersByBook(Book book) {
		// TODO Auto-generated method stub
		/*
		 * Optional<BookOrder> BookPresentorNot=bookOrderRepo.findByBook(book);
		 * 
		 * if(!BookPresentorNot.isPresent()) { //Category ob=categoryPresentorNot.get();
		 * throw new BookNotFoundException("Book with +"+book.getTitle()+" not found!");
		 * } //return null; List<Customer>
		 * listOfOrderOfCustomersByBook=bookOrderRepo.findAllByCustomer(book); return
		 * listOfOrderOfCustomersByBook;
		 */
		return null;
	}

	@Override
	public Customer viewCustomer(Integer id) {
		// TODO Auto-generated method stub
		Optional<Customer> findCustomerById = customerServiceRepo.findById(id);
		if (findCustomerById.isPresent()) {
			Customer customerToView= customerServiceRepo.getById(id);
			return customerToView;
		} else
			throw new CustomerNotFoundException("Customer not found for the entered CustomerID");
	}

	

	

}
