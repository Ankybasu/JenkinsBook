package com.cg.bookstore.exceptions;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String arg0) {
		super("Book and customer both needs to exist");
		// TODO Auto-generated constructor stub
	}
}
