package com.cg.bookstore.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.exceptions.BookAlreadyPresentException;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.OrderListEmptyException;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IOrderDetailsRepository;
import com.cg.bookstore.repository.IOrderRepositorys;

@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService{

	@Autowired
	private IOrderDetailsRepository orderRepo;
	@Autowired
	//private orders saved
	private IOrderRepositorys eachOrderDetails;
	@Autowired
	private ICustomerRepository customerRepo;
	@Autowired
	private IBookRepository bookRepo;
	@Autowired
	private IBookOrderRepository bookOrderRepo;
	
	/*
	 * views order for admin
	 */
	@Override
	public List<BookOrder> listAllOrders() {
		// TODO Auto-generated method stub
		return bookOrderRepo.findAll();
	}
	/*
	 * lists all the orders of a particular customer(Placed orders of a particular customer)
	 */
	@Override
	public ResponseEntity<?> listOrderByCustomer(Customer cs) {
		// TODO Auto-generated method stub
		Optional<BookOrder> listOrderByCustomer = bookOrderRepo.findById(cs.getCustomerId());
		try {
		if (listOrderByCustomer.isPresent()) {
			return new ResponseEntity<BookOrder>(listOrderByCustomer.get(), HttpStatus.OK);
			//return bookOrderRepo.findByCustomer(cs.getCustomerId());
		} else
			throw new OrderListEmptyException("No order Placed By the Customer");
		}catch(OrderListEmptyException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	/*
	 * The Customer 
	 */
	@Override
	public BookOrder viewOrderForCustomer(OrderDetails od) {
		// TODO Auto-generated method stub
		return bookOrderRepo.findByCustomer(od.getBookOrder().getCustomer().getCustomerId());
		
	}

	@Override
	public OrderDetails viewOrderForAdmin(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails cancelOrder(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails addOrder(OrderDetails od) {
		// TODO Auto-generated method stub
		int id=od.getBook().getBookId();
		Optional<Book> b=bookRepo.findByBookId(id);
		if(b.isPresent()) {
			double price=b.get().getPrice();
			double subtotal=price*od.getQuantity();
			Book temp=bookRepo.getById(id);
			//od.setBook(b.get());
			//od.setSubtotal(subtotal);
			return orderRepo.save(new OrderDetails(temp,od.getQuantity(), subtotal));
		}
		else 
			throw new BookNotFoundException("Book not found!!");
	}

	@Override
	public OrderDetails updateOrder(OrderDetails od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> viewOrderByBook(Book book) {
		// TODO Auto-generated method stub
		Optional<OOrder> listOrderByBook = eachOrderDetails.findAllByBook(book.getBookId());
		try {
		if (listOrderByBook.isPresent()) {
			return new ResponseEntity<OOrder>(listOrderByBook.get(), HttpStatus.OK);
			//return bookOrderRepo.findByCustomer(cs.getCustomerId());
		} else
			
			throw new OrderListEmptyException("No order Placed For the Book");
		}catch(OrderListEmptyException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Book> listBestSellingBook() {
		// TODO Auto-generated method stub
		List<OOrder> listOrders=eachOrderDetails.findAll();
//		
//		Map<BookOrder, Long> maxOrdr= 
		//listOrders.stream()
	    //.collect(Collectors.groupingBy(.g, Collectors.counting()));
//		return maxOrdr.get(eachOrderDetails);
		int highCount=0;
		for(OOrder i: listOrders) {
			
		}
		return null;
	}

	public BookOrder calculateTotal(BookOrder bookOrder) {
		// TODO Auto-generated method stub
		Customer ad=customerRepo.findById(bookOrder.getCustomer().getCustomerId()).get();
		double orderTotal=0;
		List<OrderDetails> orderList=orderRepo.findAll();
		for(OrderDetails i: orderList) {
			orderTotal+=i.getSubtotal();
		}
		System.out.println(orderTotal+""+ad);
		BookOrder temp= bookOrderRepo.save(new BookOrder(ad,bookOrder.getOrderDate(),orderTotal,"placed",
				ad.getAddress(),bookOrder.getPaymentMethod(),bookOrder.getRecipientName(),bookOrder.getRecipientPhone()));
		for(OrderDetails i: orderList) {
			i.setBookOrder(temp);
			System.out.println("bo:"+i.getBookOrder().getOrderId()+"oid:"+i.getOrderDetailsId());
			orderRepo.save(i);
			//previous mainOrder
			eachOrderDetails.save(new OOrder(i.getBook(), i.getBookOrder(), i.getQuantity(), i.getSubtotal()));
			//mainOrder.save(new OOrder(i.getBook(), i.getQuantity(), i.getSubtotal()));
			orderRepo.deleteAll();
			
		}
		return temp;
	}
	}


