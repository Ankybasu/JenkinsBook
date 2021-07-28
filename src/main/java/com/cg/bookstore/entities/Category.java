package com.cg.bookstore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="category")
@ApiModel(description="Category")
public class Category {
	
	@ApiModelProperty(notes="Id of the Category")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "category_Sequence")
    @SequenceGenerator(name = "category_Sequence", sequenceName = "CATEGORY_SEQ")
	private int categoryId;
	
	@ApiModelProperty(notes="Name of the category")
	@NotBlank(message="category is mandatory")
	@Column(name="category_name")
	private String categoryName;
	

	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Category() {
		super();
	}
	
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
