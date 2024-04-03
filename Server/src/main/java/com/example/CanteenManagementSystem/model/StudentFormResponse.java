package com.example.CanteenManagementSystem.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
public class StudentFormResponse {
	@JsonProperty("student_id")
	private int student_id;
	@JsonProperty("rfid_Number")
	private long rfid_Number;
	@JsonProperty("First_name")
	private String First_name;
	@JsonProperty("Last_name")
	private String Last_name;

	@JsonProperty("department")
	private String department;
	@JsonProperty("aadhar_number")
	private String aadhar_number;
	@JsonProperty("mob_number")
	private long mob_number;
	@JsonProperty("address")
	private String address;
	@JsonProperty("email")
	private String email;
	@JsonProperty("date_time")
	private LocalDate date_time;
	@JsonProperty("image")
	private String image;
	private List<PurchaseOrder> purchaseOrders;
	@JsonProperty("password")
	private String password;

	@JsonProperty("wallet")
	private int wallet;
	
	public StudentFormResponse() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StudentFormResponse(int student_id, long rfid_Number, String first_name, String last_name, String department,
			String aadhar_number, long mob_number, String address, String email, LocalDate date_time) {
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

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public StudentFormResponse(int student_id, long rfid_Number, String first_name, String last_name, String department,
			String aadhar_number, long mob_number, String address, String email, LocalDate date_time, String image,
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

	public void setImage(String base64Photo) {
		image = base64Photo;
	}

	@Override
	public String toString() {
		return "StudentFormResponse [student_id=" + student_id + ", rfid_Number=" + rfid_Number + ", First_name="
				+ First_name + ", Last_name=" + Last_name + ", department=" + department + ", aadhar_number="
				+ aadhar_number + ", mob_number=" + mob_number + ", address=" + address + ", email=" + email
				+ ", date_time=" + date_time + ", image=" + image + ", purchaseOrders=" + purchaseOrders + "]";
	}

	public void setDate_Time(LocalDate date_time2) {
		this.date_time = date_time2;
	}

}
