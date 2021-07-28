package com.cg.bookstore.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="booktable")
@ApiModel(description="Book")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="bookSeqGen",sequenceName="bookSeq",initialValue=100)
	@GeneratedValue(generator="bookSeqGen")
	@Column(name="book_id")
	private int bookId;
	
	@ApiModelProperty(notes="Title of the Book")
    @Size(max = 40, min = 1, message = "book title invalid")
    @NotEmpty(message = "Please enter title")
	@Column(name="title",nullable=false)
	private String title;
	
	@ApiModelProperty(notes="Author of the book")
	@NotEmpty(message="Cannot be blank")
	@Column(name="author")
	private String author;
	
	@ApiModelProperty(notes="Category of the book")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;

	@ApiModelProperty(notes="Description of the book")
	@Column(name="description")
	private String description;
	
	@ApiModelProperty(notes="ISBN of the book")
	@Column(name="isbn")
	private String isbn;
	
	@ApiModelProperty(notes="Price of the book")
	@NotNull(message="Price invalid")
	@Column(name="price",nullable=false)
	private double price;
	
	@ApiModelProperty(notes="Published date of the book")
	@Column(name="publishdate")
	private LocalDate publishDate;
	
	@ApiModelProperty(notes="Last update of the book")
	@Column(name="lastupdatedon")
	private LocalDate lastUpdatedOn;
	
//	
   // @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
   // private Set<Review> reviews = new HashSet<>();
//	
//
//
	//public Set<Review> getReviews() {
	//	return reviews;
	//}
//
	//public void setReviews(Set<Review> reviews) {
	//	this.reviews = reviews;
	//}

	public Book() {
		super();
	}
	public Book(String title, String author, String description, String isbn, double price, LocalDate publishDate, LocalDate lastUpdatedOn) {
		super();
		//this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Book(String title, String author, Category category, String description, String isbn, double price,
			LocalDate publishDate, LocalDate lastUpdatedOn) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	


	public Book(int bookId, String title, String author, Category category, String description, String isbn,double price, LocalDate publishDate, LocalDate lastUpdatedOn) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public LocalDate getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
