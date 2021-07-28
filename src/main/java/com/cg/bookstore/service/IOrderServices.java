package com.cg.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OOrder;

@Service
public interface IOrderServices {
	public List<OOrder> listAllOrders();
	public List<OOrder> listOrderByCustomer(Customer cs);
	public OOrder viewOrderForCustomer(OOrder od);
	public OOrder viewOrderForAdmin(OOrder od);
	public OOrder cancelOrder(OOrder od);
	public OOrder addOrder(OOrder od);
	public OOrder updateOrder(OOrder od);
	public List<OOrder> viewOrderByBook(Book book);
	public List<Book> listBestSellingBook();
}
