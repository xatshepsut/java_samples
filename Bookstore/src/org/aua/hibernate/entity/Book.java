package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
public class Book {
	   
	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "book_id", unique = true)
	   private int bookId;
	   
	   @Column(name="title", nullable = false, columnDefinition = "VARCHAR(45)")
	   private String title;
	   
	   @Column(name="price", nullable = false)
	   private int price;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="category_id", nullable = false)
	   private Category category;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="author_id", nullable = false)
	   private Author author;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="discount_id")
	   private Discount discount;

	   public Book() {
		   
	   }
	   public Book(String title, int price, Category category, Author author) {
	        setTitle(title);
	        setPrice(price);
	        setCategory(category);
	        setAuthor(author);
	   }
	   public int getId() {
	      return bookId;
	   }
	   public String getTitle() {
	      return title;
	   }
	   public void setTitle(String title) {
	      this.title = title;
	   }
	   public int getPrice() {
	      return price;
	   }
	   public void setPrice(int price) {
	      this.price = price;
	   }
	   public Category getCategory() {
	      return category;
	   }
	   public void setCategory(Category category) {
	      this.category = category;
	   }
	   public Author getAuthor() {
	      return author;
	   }
	   public void setAuthor(Author author) {
	      this.author = author;
	   }
	   public Discount getDiscount() {
		      return discount;
	   }
	   public void setDiscount(Discount discount) {
	      this.discount = discount;
	   }
}




