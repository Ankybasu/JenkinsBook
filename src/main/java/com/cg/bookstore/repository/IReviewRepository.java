package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer>{


	public List<Review> findByBook(int bookId);

	public List<Review> findByCustomer(Customer customer);

	public List<Review> findByBook(Book book);
//	
//	@Query("select r.rating from Review r where r.book Like %?1" )
//	 List<Review> findByBookName(String bookName);

	public List<Review> findByBook_BookId(int bookId);
	public List<Review> findByCustomer_CustomerId(int customerId);



}
