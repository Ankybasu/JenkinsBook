package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CustomerNotFoundException;
import com.cg.bookstore.exceptions.NotFoundException;
import com.cg.bookstore.exceptions.ReviewNotFoundException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IReviewRepository;


@Service
public class ReviewServiceImpl implements IReviewService{
	@Autowired 
	private IReviewRepository reviewServiceRepo;
	@Autowired
	private ICustomerRepository custRepo;
	@Autowired
	private IBookRepository bookRepo;
	
	@Override
	public List<Review> listAllReviews() {
		// TODO Auto-generated method stub
		List<Review> r= reviewServiceRepo.findAll();
		List<Review> reviewList = new ArrayList<>();
		for(Review i: r) {
			reviewList.add(new Review(i.getReviewId(),i.getCustomer().getFullName(),i.getHeadLine(),i.getComment(),i.getRating(),i.getReviewOn()));
			//reviewList.add(new Review(i.getHeadLine(),i.getComment(),i.getRating(),i.getReviewOn()));
		}
		return reviewList;
	}

	@Override
	public String deleteReview(Review review) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(review.getReviewId());
		if (findReviewById.isPresent()) {
			reviewServiceRepo.deleteById(review.getReviewId());
			return "Review Deleted!!";
		} else
			throw new ReviewNotFoundException("Review not found for the entered ReviewID");
	}
	@Override
	public Review updateReview(Review review) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(review.getReviewId());
		if (findReviewById.isPresent()) {
			reviewServiceRepo.save(review);
			return review;
		} else
			throw new ReviewNotFoundException(
					"Review with Id: " + review.getReviewId() + " not exists!!");
	}
	@Override
	public Review viewReview(int reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(reviewId);
		if (findReviewById.isPresent()) {
			return findReviewById.get();
		} else
			throw new ReviewNotFoundException("Review not found for the entered ReviewID");
		}
	@Override
	public List<Review> listAllReviewsByCustomer(int customerId) {
		// TODO Auto-generated method stub
		List<Review> reviewListByCustomer=reviewServiceRepo.findByCustomer_CustomerId(customerId);
		if(reviewListByCustomer!=null) {
		List<Review> reviewList = new ArrayList<>();
		for(Review i: reviewListByCustomer) {
			reviewList.add(new Review(i.getBook(),i.getHeadLine(),i.getComment(),i.getRating(),i.getReviewOn()));
		}
		return reviewList;
		}
		else {
			throw new CustomerNotFoundException("Customer Not Found");
		}
	}

	@Override
	public Review addReview(Review review) {
		// TODO Auto-generated method stub
		//Optional<Customer> c=reviewServiceRepo.findByCustomer_Id(review.getCustomer());
		Optional<Customer> c=custRepo.findById(review.getCustomer().getCustomerId());
		Optional<Book> b=bookRepo.findByBookId(review.getBook().getBookId());
		if(!c.isPresent()) {
			throw new CustomerNotFoundException("Customer not found!");
		}
		else if(!b.isPresent())
		{
			throw new BookNotFoundException("Book not found!");
		}
		else {
		return reviewServiceRepo.save(review);
		}
	}

	@Override
	public List<Book> listMostFavoredBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public List<Review> listAllReviewsByBook(Book book) { // TODO
	 * Auto-generated method stub List<Review>
	 * reviewListByBook=reviewServiceRepo.findByBook(book);
	 * if(reviewListByBook!=null) { List<Review> reviewList = new ArrayList<>();
	 * for(Review i: reviewListByBook) { reviewList.add(new
	 * Review(i.getHeadLine(),i.getComment(),i.getRating(),i.getReviewOn())); }
	 * return reviewList; } else { throw new
	 * BookNotFoundException("Book Not present"); } }
	 */
	@Override
	public List<Review> listAllReviewsByBook(int bookId) {
		// TODO Auto-generated method stub
		List<Review> reviewListByBook=reviewServiceRepo.findByBook_BookId(bookId);
		List<Review> reviewList = new ArrayList<>();
		if(!reviewListByBook.isEmpty()) {
		for(Review i: reviewListByBook) {
			reviewList.add(new Review(i.getHeadLine(),i.getComment(),i.getRating(),i.getReviewOn()));
		}
		return reviewList;
		}
		else {
			throw new BookNotFoundException("Book Not Found");
		}
	}
	public ResponseEntity<String> addReviewByParam(int bookId,String desc,int custid,String headLine,String comment,double rating) {
		Optional<Book> b=bookRepo.findById(bookId);
		Optional<Customer> c=custRepo.findById(custid);
		LocalDate d=LocalDate.now();
		if(b.isPresent()&&c.isPresent()) {
		Review r=new Review(b.get(), c.get(), headLine, comment, rating,d);
		reviewServiceRepo.save(r);
		}
		else {
			return new ResponseEntity<>("Customer/Book not present",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Review added", HttpStatus.OK);
	}

}