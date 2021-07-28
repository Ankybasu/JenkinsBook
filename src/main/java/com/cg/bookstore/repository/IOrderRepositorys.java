package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.OOrder;
import com.cg.bookstore.entities.OrderDetails;

@Repository
public interface IOrderRepositorys extends JpaRepository<OOrder, Integer>{

	public Optional<OOrder> findByBook(int i);

	public Optional<OOrder> findAllByBook(int bookId);
	//@Query("select bookId, from OOrder ")
}
