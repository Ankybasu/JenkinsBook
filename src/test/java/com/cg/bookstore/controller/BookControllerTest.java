package com.cg.bookstore.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mockito.BDDMockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.BookServiceImpl;
import com.cg.bookstore.service.CategoryServiceImpl;
import com.cg.bookstore.service.IBookService;

//@WebMvcTest(controllers= BookController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private IBookRepository bookRepo;
	
	/*
	 * @InjectMocks BookServiceImpl bookService;
	 */
    
	@MockBean                       
	private BookServiceImpl bookService;
	
	public List<Book> bookList;
	public List<Category> category;
	//int bookId, String title, String author, Category category, String description, String isbn, double price,
	//LocalDate publishDate, LocalDate lastUpdatedOn
    @BeforeEach                           
    public void setUp() throws Exception { 
    	  LocalDate d1 = LocalDate.of(1988, 5, 5);
    	  LocalDate d2 = LocalDate.now();
    	this.category.add(new Category(1,"Fiction"));
       this.bookList = new ArrayList<>();                                    
       this.bookList.add(new Book(201, "JK","HP", category.get(0), "Nice", "133545", 800,d1,d2));
       this.bookList.add(new Book(202, "JK","HP", category.get(0), "Nice", "133545", 800,d1,d2));
   	//bookRepo.saveAll(bookList);
       //this.bookList.add(new Book());
      // this.bookList.add(new Book());
    }
    @Test
    public void testgetBooks() throws Exception{
    	//when(this.bookService.listAllBooks()).thenReturn(bookList);
    	given(this.bookService.listAllBooks()).willReturn(bookList);
  	  LocalDate d1 = LocalDate.of(1988, 5, 5);
  	  LocalDate d2 = LocalDate.now();
    	//Book b=new Book(201, "JK","HP", category.get(0), "Nice", "133545", 800,d1,d2);
    	//bookRepo.save(b);
    	BookServiceImpl myService = Mockito.mock(BookServiceImpl.class);
    	assertEquals(201,myService.listAllBooks());
    	this.mockMvc.perform(get("/book/getallbooks"))
    	.andExpect(status().isOk());
    	
    }
}