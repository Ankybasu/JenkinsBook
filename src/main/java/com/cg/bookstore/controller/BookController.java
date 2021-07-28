package com.cg.bookstore.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.exceptions.BookAlreadyPresentException;
import com.cg.bookstore.exceptions.BookListEmptyException;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.service.BookServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;


@Api(value="book",description="Operations pertaining to book")
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookServiceImpl bookService;
	
	@ApiOperation(value="add book details",notes="To add a book by the admin",response=Contact.class)
	@PostMapping("/addbook")
    @ExceptionHandler(BookAlreadyPresentException.class)
    ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
        // persisting the book
    	bookService.createBook(book);
        return ResponseEntity.ok("Book "+book.getTitle()+" is added");	
		
	}
	@ApiOperation(value="views all the book details",notes="To view all the books present",response=Contact.class)
	@GetMapping("/getallbooks")
	public List<Book> getBooks(){
		return bookService.listAllBooks();
	}
	@ApiOperation(value="search book by id",notes="To search a book by the book id given",response=Contact.class)
	@GetMapping("/searchbookbyid/{id}")
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<?> searchBookByID(@PathVariable("id") Integer bookId) {

		return bookService.findBookById(bookId);
	}
	@ApiOperation(value="views books by category",notes="To display the books according to the category",response=Contact.class)
	@GetMapping("/showbookbycategory/{category}")
	public List<Book> getBookByCategory(@PathVariable("category") String category){
		return bookService.listBooksByCategory(category);
	}
	@ApiOperation(value="view books by book name entered",notes="displays the books according to the book name entered",response=Contact.class)
	@GetMapping("/viewbookbyname/{name}")
	public List<Book> getBooksByName(@PathVariable("name") String bookName){
		return bookService.viewBookByName(bookName);
	}

	@ApiOperation(value="update an existing book",notes="To change a book's details by the admin",response=Contact.class)
	@PutMapping("/updatebook")
	@ExceptionHandler(BookAlreadyPresentException.class)
	public Book updateBook(@RequestBody Book book){
		return bookService.editBook(book);
	}
	
	@ApiOperation(value="delete a book by the book id",notes="To delete a book by the book id entered by the admin",response=Contact.class)
	@DeleteMapping("/deletebook/{id}")
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> deleteBookingByID(@PathVariable("id") Integer bookId) {

		return bookService.deleteBook(bookId);
	}

}
