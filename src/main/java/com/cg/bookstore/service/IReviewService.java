package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;

public interface IReviewService {

	public List<Review> listAllReviews();
	public Review addReview(Review review);
	public String deleteReview(Review review);
	public Review updateReview(Review review);
	public List<Book> listMostFavoredBooks();
	//public Review viewReview(Integer reviewId);
	//public List<Review> listAllReviewsByBook(Book b);
	public List<Review> listAllReviewsByCustomer(int custId);
	Review viewReview(int reviewId);
	//List<Review> listAllReviewsByBookQuery(Book book);
	List<Review> listAllReviewsByBook(int bookId);
}