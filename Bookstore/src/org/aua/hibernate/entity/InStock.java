package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "INSTOCK")
public class InStock {

	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "id", unique = true)
	   private int id;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="book_id", nullable = false)
	   private Book book;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="store_id", nullable = false)
	   private Store store;
	   
	   @Column(name="quantity", nullable = false)
	   private int quantity;

	   public InStock() {
		   
	   }
	   public InStock(Book book, Store store, int quantity) {
	      setBook(book);
	      setStore(store);
	      setQuantity(quantity);
	   }
	   public Store getStore() {
	      return store;
	   }
	   public void setStore(Store store) {
	      this.store = store;
	   }
	   public Book getBook() {
	      return book;
	   }
	   public void setBook(Book book) {
	      this.book = book;
	   }
	   public int getQuantity() {
	      return quantity;
	   }
	   public void setQuantity(int quantity) {
	      this.quantity = quantity;
	   }
}
