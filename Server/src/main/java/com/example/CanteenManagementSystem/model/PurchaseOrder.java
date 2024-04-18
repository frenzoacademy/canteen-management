//package com.example.CanteenManagementSystem.model;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//public class PurchaseOrder {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int order_id;
//	private float quantity;
//	private int totalAmount;
//	private Date date_time;
//	private String status;
//
//	 @ManyToOne
//	    @JoinColumn(name = "student_id") // Assuming the foreign key column name
//	    private StudentForm studentForm;
//
//	    @ManyToMany
//	    @JoinTable(name = "purchaseorder_foodinventory",
//	               joinColumns = @JoinColumn(name = "order_id"),
//	               inverseJoinColumns = @JoinColumn(name = "food_id"))
//	    private List<FoodInventory> foodItems;
//	
////	@ManyToMany(mappedBy = "purchaseOrders")
////	private List<StudentForm> studentForms;
//
//	public PurchaseOrder(int food_id, float quantity, int totalAmount, Date date_time, String status,
//			List<FoodInventory> studentForms) {
//		super();
//		order_id = food_id;
//		this.quantity = quantity;
//		this.totalAmount = totalAmount;
//		this.date_time = date_time;
//		this.status = status;
////		this.student_id = student_id;
//		this.foodItems = studentForms;
//	}
//
//	public int getOrder_id() {
//		return order_id;
//	}
//
//	public void setOrder_id(int order_id) {
//		this.order_id = order_id;
//	}
//
//	public StudentForm getStudentForm() {
//		return studentForm;
//	}
//
//	public void setStudentForm(StudentForm studentForm) {
//		this.studentForm = studentForm;
//	}
//
//	public List<FoodInventory> getFoodItems() {
//		return foodItems;
//	}
//
//	public void setFoodItems(List<FoodInventory> foodItems) {
//		this.foodItems = foodItems;
//	}
//
//	public PurchaseOrder() {
//		super();
//	}
//
//	public int getFood_id() {
//		return order_id;
//	}
//
//	public void setFood_id(int food_id) {
//		order_id = food_id;
//	}
//
//	public float getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(float quantity) {
//		this.quantity = quantity;
//	}
//
//	public int getTotalAmount() {
//		return totalAmount;
//	}
//
//	public void setTotalAmount(int totalAmount) {
//		this.totalAmount = totalAmount;
//	}
//
//	public Date getDate_time() {
//		return date_time;
//	}
//
//	public void setDate_time(Date date_time) {
//		this.date_time = date_time;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
////	public int getStudent_id() {
////		return student_id;
////	}
////
////	public void setStudent_id(int student_id) {
////		this.student_id = student_id;
////	}
//
//	
//
//}
package com.example.CanteenManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    private float quantity;
    private int totalAmount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDate date_time;
    private String status;
    private int food_id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentForm studentForm;

    @ManyToMany
    @JoinTable(name = "purchaseorder_foodinventory",
               joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<FoodInventory> foodItems;

    
    public StudentForm getStudentForm() {
        return studentForm;
    }
    
    public PurchaseOrder() {
		super();
        this.foodItems = new ArrayList<>(); // Initialize foodItems with an empty list
	}

	public PurchaseOrder(int order_id, float quantity, int totalAmount, LocalDate date_time, String status, List<FoodInventory> foodItems) {
        this.order_id = order_id;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.date_time = date_time;
        this.status = status;
        this.foodItems = foodItems;
    }
	

    public PurchaseOrder(int order_id, float quantity, int totalAmount, LocalDate date_time, String status,
			StudentForm studentForm, List<FoodInventory> foodItems) {
		super();
		this.order_id = order_id;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.date_time = date_time;
		this.status = status;
		this.studentForm = studentForm;
		this.foodItems = foodItems;
	}

	public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public LocalDate getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDate date_time) {
        this.date_time = date_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setStudentForm(StudentForm studentForm) {
        this.studentForm = studentForm;
    }

    public List<FoodInventory> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodInventory> foodItems) {
        this.foodItems = foodItems;
    }

	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	
	

}
