package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

class CategoryServiceImplTest {
	private CategoryServiceImpl categoryService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("should print before each test case");
		System.out.println("Instantitating the Category Service");

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Should create category")
	void testAddCategory() {
	//	String ac=categoryService.addCategory("Fiction");
		String actual=categoryService.addCategory("Science");
		assertEquals("Category Science is valid", actual);
	}

	@Test
	void testEditCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testViewAllCategories() {
		fail("Not yet implemented");
	}

}
