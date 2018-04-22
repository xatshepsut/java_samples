package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name = "employee_id", unique = true)
	  private int employeeId;
	   
	  @Column(name="full_name", nullable = false, columnDefinition = "VARCHAR(45)")
	  private String fullName;
	  
	  @Column(name="salary", nullable = false)
	  private int salary;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="store_id", nullable = false)
	  private Store store;

	   public Employee() {
		   
	   }
	   public Employee(String name, int salary, Store store) {
	        setFullName(name);
	        setSalary(salary);
	        setStore(store);
	   }
	   public int getId() {
	      return employeeId;
	   }
	   public String getFullName() {
	      return fullName;
	   }
	   public void setFullName(String name) {
	      this.fullName = name;
	   }
	   public int getSalary() {
		      return salary;
	   }
	   public void setSalary(int salary) {
	      this.salary = salary;
	   }
	   public Store getStrore() {
		      return store;
	   }
	   public void setStore(Store store) {
	      this.store = store;
	   }
}





