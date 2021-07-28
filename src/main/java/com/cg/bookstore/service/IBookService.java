package com.cg.bookstore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;

@Service
public interface IBookService {
	public Book createBook(Book b);
	public List<Book> listAllBooks();
	public Book editBook(Book book);
	//public Book viewBook(String bookName);
	public List<Book> listBooksByCategory(String cat);
	public List<Book> viewBookByName(String bookName);
	public ResponseEntity<String> deleteBook(Integer bookId);
	public ResponseEntity<?> findBookById(Integer bid);
}
