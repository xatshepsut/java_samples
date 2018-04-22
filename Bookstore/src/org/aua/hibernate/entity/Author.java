package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR")
public class Author {

	  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name = "author_id", unique = true)
	  private int authorId;
	   
	  @Column(name="full_name", nullable = false, columnDefinition = "VARCHAR(45)")
	  private String fullName;
	  
	  @Column(name="country", nullable = false, columnDefinition = "VARCHAR(20)")
	  private String country;

	   public Author() {
		   
	   }
	   public Author(String name, String country) {
	        setFullName(name);
	        setCountry(country);
	   }
	   public int getId() {
	      return authorId;
	   }
	   public String getFullName() {
	      return fullName;
	   }
	   public void setFullName(String name) {
	      this.fullName = name;
	   }
	   public String getCountry() {
		      return country;
	   }
	   public void setCountry(String country) {
	      this.country = country;
	   }
}





