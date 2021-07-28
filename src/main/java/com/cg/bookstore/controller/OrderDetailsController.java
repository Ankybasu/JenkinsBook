package com.cg.bookstore.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.OrderListEmptyException;
import com.cg.bookstore.exceptions.OrderNotPlacedException;
import com.cg.bookstore.repository.IOrderDetailsRepository;
import com.cg.bookstore.service.OrderDetailsServiceImpl;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsServiceImpl orderDetailsService;
	//@Autowired
	//private BookOrderServiceImpl bookOrderService; 
	@Autowired
	private IOrderDetailsRepository op;
	/*/
	 * All orders of the customers by the Admin
	 */
	@GetMapping("/vieworderdetailss")
	    List<BookOrder> listAllOrders(){
	        return orderDetailsService.listAllOrders();
		}
	/*
	 * add order to a cart by the customer
	 */
	
	@PostMapping("/addtocartd")
    @ExceptionHandler(OrderNotPlacedException.class)
    ResponseEntity<String> addOrder(@Valid @RequestBody OrderDetails od){
        // persisting the order
		orderDetailsService.addOrder(od);
        return ResponseEntity.ok("Order added to cart!");	
	}
	/*
	 * checkout by a customer(final placing an order)
	 */
	@PostMapping("/checkoutd")
    @ExceptionHandler(OrderNotPlacedException.class)
    BookOrder placeOrder(@RequestBody BookOrder bookOrder){
		return orderDetailsService.calculateTotal(bookOrder);
        //return ResponseEntity.ok("Order placed!");	
}
	/*
	 * view ordersBycustomerid
	 */
	@GetMapping("/viewordersbycustomer")
	@ExceptionHandler(OrderListEmptyException.class)
	public ResponseEntity<?> searchBookByID(@RequestBody Customer cs) {

		return orderDetailsService.listOrderByCustomer(cs);
	}
	
}