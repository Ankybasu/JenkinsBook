package com.cg.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="logintable")
public class Login {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "category_Sequence")
	@Column(name="loginid")
	private int loginId;
	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
//	//@OneToOne
//	@MapsId
//	@JoinColumn(name="cust_id")
//	private int loginId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Login() {
		super();
	}
	/*
	 * public int getLoginId() { return loginId; }
	 * 
	 * public void setLoginId(int loginId) { this.loginId = loginId; }
	 */

	//public Login(int loginId2, String email2, String password2) {
		// TODO Auto-generated constructor stub
	//}

	

}