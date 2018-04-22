package org.aua.hibernate.entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "SALE")
public class Sale {
	   
	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "sale_id")
	   private int saleId;
		
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="book_id", nullable = false)
	   private Book book;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="employee_id", nullable = false)
	   private Employee employee;
	   
	   @Column(name="value", nullable = false)
	   private int value;
	   
	   @Column(name="timestamp", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	   private Timestamp timestamp;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="customer_id" )
	   private Customer customer;

	   public Sale() {
		   
	   }
	   public Sale(Book book, Employee employee, int value) {
	      setBook(book);
	      setEmployee(employee);
	      setValue(value);
	      
	      java.util.Date date= new java.util.Date();
	      this.timestamp = new Timestamp(date.getTime());
	   }
	   public int getId() {
	      return saleId;
	   }
	   public Book getBook() {
	      return book;
	   }
	   public void setBook(Book book) {
	      this.book = book;
	   }
	   public Employee getEmployee() {
	      return employee;
	   }
	   public void setEmployee(Employee employee) {
	      this.employee = employee;
	   }
	   public int getValue() {
	      return value;
	   }
	   public void setValue(int value) {
	      this.value = value;
	   }
	   public Timestamp getTimestamp() {
	      return timestamp;
	   }
	   public Customer getCustomer() {
	      return customer;
	   }
	   public void setCustomer(Customer customer) {
	      this.customer = customer;
	   }
}
