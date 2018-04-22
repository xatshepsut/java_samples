package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {

	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "category_id", unique = true)
	   private int categoryId;
	   
	   @Column(name="name", nullable = false, columnDefinition = "VARCHAR(20)")
	   private String name;

	   public Category() {
		   
	   }
	   public Category(String name) {
	        setName(name);
	   }
	   public int getId() {
	      return categoryId;
	   }
	   public String getName() {
	      return name;
	   }
	   public void setName(String name) {
	      this.name = name;
	   }
}




