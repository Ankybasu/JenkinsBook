package com.cg.bookstore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.exceptions.OrderListEmptyException;
import com.cg.bookstore.entities.OrderDetails;

@Service
public interface IOrderDetailsService {
	public List<BookOrder> listAllOrders();
	public ResponseEntity<?> listOrderByCustomer(Customer cs);
	public BookOrder viewOrderForCustomer(OrderDetails od);
	public OrderDetails viewOrderForAdmin(OrderDetails od);
	public OrderDetails cancelOrder(OrderDetails od);
	public OrderDetails addOrder(OrderDetails od);
	public OrderDetails updateOrder(OrderDetails od);
	public ResponseEntity<?> viewOrderByBook(Book book);
	public List<Book> listBestSellingBook();
}
