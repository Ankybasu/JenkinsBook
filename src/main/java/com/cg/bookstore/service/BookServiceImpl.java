package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.BookAlreadyPresentException;
import com.cg.bookstore.exceptions.BookListEmptyException;
import com.cg.bookstore.exceptions.BookNotFoundException;
import com.cg.bookstore.exceptions.CategoryAlreadyPresentException;
import com.cg.bookstore.exceptions.CategoryNotFoundException;
import com.cg.bookstore.exceptions.PriceInvalidException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICategoryRepository;

@Service
public class BookServiceImpl implements IBookService{
	@Autowired 
	private IBookRepository bookServiceRepo;
	
	@Autowired
	private ICategoryRepository categoryRepo;
	
	@Override
	  public Book createBook(Book book) { 
	 Optional<Book> findBookById = bookServiceRepo.findById(book.getBookId()); 
	 Optional<Category> c=categoryRepo.findByCategoryName(book.getCategory().getCategoryName());
	 try
	  { //category present but book not present
		 if (c.isPresent() &&!findBookById.isPresent()) { 
			 Book b= bookServiceRepo.save(new Book(book.getTitle(),book.getAuthor(),book.getDescription(), book.getIsbn(),
	  book.getPrice(), book.getPublishDate(), book.getLastUpdatedOn())); 
			 b.setCategory(c.get());
			 return bookServiceRepo.save(b);
			 } else {
				 //when both category and book not present
				 return bookServiceRepo.save(book); } 
		 } catch(PriceInvalidException e) { 
			 throw new PriceInvalidException("Price Invalid!"); 
			 }
	  catch(BookAlreadyPresentException e1) { 
		  throw new BookAlreadyPresentException("Book already present exception!"); }	 
	}
	@Override
	public List<Book> listAllBooks() {
		// TODO Auto-generated method stub
		List<Book> listOfBooks= bookServiceRepo.findAll();
		return listOfBooks;
		} 


	@Override
	public Book editBook(Book book) {
		// TODO Auto-generated method stub
		Optional<Book> findBookById = bookServiceRepo.findById(book.getBookId());
		if (findBookById.isPresent()) {
			 Optional<Category> c=categoryRepo.findByCategoryName(book.getCategory().getCategoryName());
			 book.setCategory(c.get());
			 return bookServiceRepo.save(book);
		} else
			throw new BookNotFoundException(
					"Book with Id: " + book.getBookId() + " not exists!!");
	}


	@Override
	public List<Book> listBooksByCategory(String categoryName) {
		// TODO Auto-generated method stub
		Optional<Category> categoryPresentorNot=categoryRepo.findByCategoryName(categoryName);
		if(!categoryPresentorNot.isPresent()) {
			throw new CategoryNotFoundException("Book with +"+categoryName+" not found!");
		}
		//return null;
		return bookServiceRepo.findByCategory(categoryPresentorNot.get());
	}

	@Override
	public List<Book> viewBookByName(String bookName) {
		// TODO Auto-generated method stub
		return bookServiceRepo.findByTitle(bookName);
	}

	@Override
	public ResponseEntity<String> findBookById(Integer bid) {
		// TODO Auto-generated method stub
			Optional<Book> findById = bookServiceRepo.findById(bid);
			try {
				if (findById.isPresent()) {
					Book findBook = findById.get();
					return new ResponseEntity<>("Book "+findBook.getTitle()+" is found", HttpStatus.OK);
				} else
					throw new BookNotFoundException("No record found with ID " + bid);
			} catch (BookNotFoundException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}

	@Override
	public ResponseEntity<String> deleteBook(Integer bookId) {
		Optional<Book> findBookById = bookServiceRepo.findById(bookId);
		if (findBookById.isPresent()) {
			bookServiceRepo.deleteById(bookId);
			//return "Book Deleted!!";
			return new ResponseEntity<>("Book deleted",HttpStatus.OK);
		} else
			throw new BookNotFoundException("Book not found for the entered BookID");
	}
}




