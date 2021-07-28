package com.cg.bookstore.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.ICategoryRepository;
import com.cg.bookstore.service.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import oracle.jdbc.driver.json.tree.JsonpPrimitive.JsonpStringImpl;


@RunWith(SpringRunner.class)
//
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@WebMvcTest(controllers=CategoryController.class)
//@ContextConfiguration(locations = "classpath:test-app-context.xml")
public class CategoryControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	//private ICategoryRepository categoryRepo;
	private CategoryServiceImpl categoryService;
	private List<Category> list;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
//		Mockito.when(this.categoryRepo.findAll())
//		.thenReturn(Collections.singletonList(new Category(1,"Fiction")));
		 list=new ArrayList<>();
		this.list.add(new Category(1, "Fiction"));
		this.list.add(new Category(2, "Science"));
		this.list.add(new Category(3, "Action"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCategory() throws Exception{
		//fail("Not yet implemented");
		ResultActions s=this.mockMvc.perform(MockMvcRequestBuilders.post("/category/addcategory").param("categoryName", "Drama"))
        .andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=UTF-8"))
        .andExpect(content().string("Category Drama is valid"));
		//assertEquals("Category Drama is valid",);
	//	String json = s.getResponse().getContentAsString();
		//application/json
		//System.out.println(json);
		   //JSONAssert.assertEquals("Category Drama is valid",json, false); 
		//CustomResponseHandler<Category> responseHandler = new CustomResponseHandler(Category.class);
		// String someClass = new ObjectMapper().readValue(json, String.class);
		//System.out.println(someClass);

		//assertEquals("Category Drama is valid",someClass);
		//assertEquals("Category Drama is valid",);
		
	}

	@Test
	public void testGetAllCategories() throws Exception {
		Mockito.when(this.categoryService.viewAllCategories())
		.thenReturn(list);
		
		//
		//Mockito.when(this.categoryRepo.findAll())//for testing repos
		//.thenReturn(list);
		//.thenReturn(Collections.singletonList(new Category(1,"Fiction")));
		//Category c=new Category(1,"Fiction");
		this.mockMvc.perform(MockMvcRequestBuilders.get("/category/viewallcategories"))
		.andExpect(status().isOk());
		//.andExpect(jsonPath("$.size", is(list.size())));
		//.andExpect(jsonPath("$.categoryName",is(c.getCategoryName())));
	}

	
	  @Test 
	  public void testUpdateCategory() throws Exception{ 
		  // fail("Not yet implemented"); 
			this.mockMvc.perform(MockMvcRequestBuilders.put("/category/updatecategory")
			           //.accept(MediaType.APPLICATION_JSON)
			           .content("{\"categoryId\":1\",\"categoryName\":\"Romance\"}")
			           .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
			           .andExpect(status().isOk());
			        ; 
		  
		  }
}
	  
	  //@Test void testDeleteBookingByID() { //fail("Not yet implemented"); }
	 