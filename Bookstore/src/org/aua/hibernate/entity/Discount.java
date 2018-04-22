package org.aua.hibernate.entity;


import javax.persistence.*;

@Entity
@Table(name = "DISCOUNT")
public class Discount {
	   
	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "discount_id", unique = true)
	   private int discountId;   
	   
	   @Column(name="point_margin", nullable = false)
	   private int pointMargin;
	   
	   @Column(name="percentage", nullable = false, columnDefinition = "DECIMAL(2,0)")
	   private int percentage;

	   public Discount() {
		   
	   }
	   public Discount(int margin, int percentage) {
	      setPointMargin(margin);
	      setPercentage(percentage);
	   }
	   public int getId() {
	      return discountId;
	   }
	   public int getPointMargin() {
	      return pointMargin;
	   }
	   public void setPointMargin(int margin) {
	      this.pointMargin = margin;
	   }
	   public int getPercentage() {
	      return percentage;
	   }
	   public void setPercentage(int percentage) {
	      this.percentage = percentage;
	   }
}
