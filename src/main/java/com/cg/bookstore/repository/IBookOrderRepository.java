package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;

@Repository
public interface IBookOrderRepository extends JpaRepository<BookOrder, Integer>{

	public BookOrder findByCustomer(int customerId);



}
