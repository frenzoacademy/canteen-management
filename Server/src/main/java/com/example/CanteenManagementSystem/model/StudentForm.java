package com.example.CanteenManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.hibernate.annotations.Type;

@Entity
public class StudentForm implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	private long rfid_Number;
	private String First_name;
	private String Last_name;
	private String department;
	private String aadhar_number;
	private long mob_number;
	private String address;
	private String email;
	private LocalDate date_time;
	private String password;
	private int wallet;

    private boolean student;

	 @Lob
	 private String formJson;
	
	 @Lob
	@JsonIgnore
	private Blob image;

	@JsonIgnore // Ignore imageData during serialization
	private byte[] imageData;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "studentForm")
    private List<PurchaseOrder> purchaseOrders;

	public StudentForm(int student_id, long rfid_Number, String first_name, String last_name, String department,
			String aadhar_number, long mob_number, String address, String email, LocalDate date_time, String password,
			Blob image, byte[] imageData, List<PurchaseOrder> purchaseOrders,int wallet) {
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
		this.password = password;
		this.image = image;
		this.imageData = imageData;
		this.purchaseOrders = purchaseOrders;
		this.wallet=wallet;
	}

	public StudentForm() {
		super();
	}

	public int getStudent_id() {
		return student_id;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public long getRfid_Number() {
		return rfid_Number;
	}

	public void setRfid_Number(long rfid_Number) {
		this.rfid_Number = rfid_Number;
	}

	public String getFirst_name() {
		return First_name;
	}

	public void setFirst_name(String first_name) {
		First_name = first_name;
	}

	public String getLast_name() {
		return Last_name;
	}

	public void setLast_name(String last_name) {
		Last_name = last_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAadhar_number() {
		return aadhar_number;
	}

	public void setAadhar_number(String aadhar_number) {
		this.aadhar_number = aadhar_number;
	}

	public long getMob_number() {
		return mob_number;
	}

	public void setMob_number(long mob_number) {
		this.mob_number = mob_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate_time() {
		return date_time;
	}

	public void setDate_time(LocalDate date_time) {
		this.date_time = date_time;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob photoBlob) {
		this.image = photoBlob;
	}

	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "StudentForm [student_id=" + student_id + ", rfid_Number=" + rfid_Number + ", First_name=" + First_name
				+ ", Last_name=" + Last_name + ", department=" + department + ", aadhar_number=" + aadhar_number
				+ ", mob_number=" + mob_number + ", address=" + address + ", email=" + email + ", date_time="
				+ date_time + ", image=" + image + ", imageData=" + Arrays.toString(imageData) + ", purchaseOrders="
				+ purchaseOrders + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStudent() {
		return true;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

}
