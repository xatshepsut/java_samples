package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "customer_id", unique = true)
	   private int customerId;
	   
	   @Column(name="points", nullable = false, columnDefinition = "INT default '0'")
	   private int points;

	   public Customer() {
		   
	   }
	   public int getCustomerId() {
	      return customerId;
	   }
	   public int getPoints() {
	      return points;
	   }
	   public void setPoints(int points) {
	      this.points = points;
	   }
}
