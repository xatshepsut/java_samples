package org.aua.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {

	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "id", unique = true)
	   private int id;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="employee_id", nullable = false)
	   private Employee employee;
	   
	   @Column(name="phone_number", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
	   private String phoneNumber;

	   public Contact() {
		   
	   }
	   public Contact(Employee employee, String number) {
	      setEmployee(employee);
	      setPhoneNumber(number);
	   }
	   public Employee getEmployee() {
	      return employee;
	   }
	   public void setEmployee(Employee employee) {
	      this.employee = employee;
	   }
	   public String getPhoneNumber() {
	      return phoneNumber;
	   }
	   public void setPhoneNumber(String phoneNumber) {
	      this.phoneNumber = phoneNumber;
	   }
}
