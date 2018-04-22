package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "STORE")
public class Store {

	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "store_id")
	   private int storeId;
	   
	   @Column(name="address", nullable = false, columnDefinition = "VARCHAR(45)")
	   private String address;

	   public Store() {
		   
	   }
	   public Store(String address) {
	        setAddress(address);
	   }
	   public int getId() {
	      return storeId;
	   }
	   public String getAddress() {
	      return address;
	   }
	   public void setAddress(String address) {
	      this.address = address;
	   }
}




