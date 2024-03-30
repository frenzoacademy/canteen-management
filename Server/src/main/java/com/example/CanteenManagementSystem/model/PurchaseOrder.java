package com.example.CanteenManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
public class PurchaseOrder {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int Food_id;
		private float quantity;
	    private int totalAmount;
	    private Date date_time;
	    private String  status;
	    private int student_id;

	    
	
	    @ManyToMany(mappedBy = "purchaseOrders")
	    private List<StudentForm> studentForms;



		public PurchaseOrder(int food_id, float quantity, int totalAmount, Date date_time, String status,
				int student_id, List<StudentForm> studentForms) {
			super();
			Food_id = food_id;
			this.quantity = quantity;
			this.totalAmount = totalAmount;
			this.date_time = date_time;
			this.status = status;
			this.student_id = student_id;
			this.studentForms = studentForms;
		}



		public PurchaseOrder() {
			super();
			// TODO Auto-generated constructor stub
		}



		public int getFood_id() {
			return Food_id;
		}



		public void setFood_id(int food_id) {
			Food_id = food_id;
		}



		public float getQuantity() {
			return quantity;
		}



		public void setQuantity(float quantity) {
			this.quantity = quantity;
		}



		public int getTotalAmount() {
			return totalAmount;
		}



		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}



		public Date getDate_time() {
			return date_time;
		}



		public void setDate_time(Date date_time) {
			this.date_time = date_time;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}



		public int getStudent_id() {
			return student_id;
		}



		public void setStudent_id(int student_id) {
			this.student_id = student_id;
		}



		public List<StudentForm> getStudentForms() {
			return studentForms;
		}



		public void setStudentForms(List<StudentForm> studentForms) {
			this.studentForms = studentForms;
		}


}