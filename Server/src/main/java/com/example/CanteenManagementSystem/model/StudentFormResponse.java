package com.example.CanteenManagementSystem.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentFormResponse {
	private int student_id;
    private long rfid_Number;   
    private String First_name;
    private String Last_name;
    private String department;
    private String aadhar_number;
    private long mob_number;
    private String address;
    private String email;
    private Date date_time;
    private String image;
    private List<PurchaseOrder> purchaseOrders;
    
    public StudentFormResponse(int student_id, long rfid_Number, String first_name, String last_name, String department,
			String aadhar_number, long mob_number, String address, String email, Date date_time) {
		super();
		this.student_id = student_id;
		this.rfid_Number = rfid_Number;
		First_name = first_name;
		Last_name = last_name;
		this.department = department;
		this.aadhar_number = aadhar_number;
		this.mob_number = mob_number;
		this.address = address;
		this.email = email;
		this.date_time = date_time;
    }

	public StudentFormResponse(int student_id, long rfid_Number, String first_name, String last_name, String department,
			String aadhar_number, long mob_number, String address, String email, Date date_time, String image,
			List<PurchaseOrder> purchaseOrders) {
		this.student_id = student_id;
		this.rfid_Number = rfid_Number;
		First_name = first_name;
		Last_name = last_name;
		this.department = department;
		this.aadhar_number = aadhar_number;
		this.mob_number = mob_number;
		this.address = address;
		this.email = email;
		this.date_time = date_time;
		this.image = image;
		this.purchaseOrders = purchaseOrders;
	}   
	    
}
