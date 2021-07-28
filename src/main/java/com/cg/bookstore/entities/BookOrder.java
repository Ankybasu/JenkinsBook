package com.cg.bookstore.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookordertable")
public class BookOrder{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bookorder_id")
	private int orderId;
	
	@OneToOne
	//@MapsId
	@JoinColumn(name="cust_id")
	private Customer customer;
	
	@Column(name="orderdate")
	private LocalDate orderDate;
	
	@Column(name="ordertotal")
	private double orderTotal;
	
	@Column(name="status")
	private String status;
	
	@OneToOne
	@JoinColumn(name="shippingaddress")
	private Address shippingAddress;
	
	/*
	 * bookorder_id will be same as the order_id of the order table
	 */
	
	//now not mapped(to be included)
//	@OneToMany(targetEntity = BookOrder.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
//	@JoinColumn(name="order_idofordert",referencedColumnName = "bookorder_id")
//	private List<OOrder> oOrder;
	
	//last
	
	//@OneToMany(mappedBy = "bookOrder",orphanRemoval = true)
	//private List<OrderDetails> oOrder;
	
	
//	public List<OrderDetails> getoOrder() {
//		return oOrder;
//	}
//	public void setoOrder(List<OrderDetails> oOrder) {
//		this.oOrder = oOrder;
//	}
	@Column(name="paymentmethod")
	private String paymentMethod;
	
	@Column(name="recipentname")
	private String recipientName;
	
	@Column(name="recipentphone")
	private String recipientPhone;
	
	public BookOrder(Customer customer, LocalDate orderDate, String paymentMethod, String recipientName,
			String recipientPhone) {
		super();
		this.customer = customer;
		this.orderDate = orderDate;
		this.paymentMethod = paymentMethod;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
	}
	public BookOrder() {
		super();
	}
	public BookOrder(Customer customer, LocalDate orderDate, double orderTotal, String status, Address shippingAddress,
			String paymentMethod, String recipientName, String recipientPhone) {
		super();
		this.customer = customer;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.status = status;
		this.shippingAddress = shippingAddress;
		this.paymentMethod = paymentMethod;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
	}
	public int getOrderId() {
		return orderId;
	}
//	public List<OOrder> getOrder() {
//		return oOrder;
//	}
//	public void setOrder(List<OOrder> oOrder) {
//		this.oOrder = oOrder;
//	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getshippingAddress() {
		return shippingAddress;
	}
	public void setshippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientPhone() {
		return recipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
}