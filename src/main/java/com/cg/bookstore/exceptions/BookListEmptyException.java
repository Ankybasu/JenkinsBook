package com.cg.bookstore.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BookListEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookListEmptyException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
