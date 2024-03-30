package com.example.CanteenManagementSystem.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CanteenManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String First_name;
    private String Last_name;
    private String role;
    private String email;
    private String address;
    private long aadhar_number;
    private String password;
    private String confirm_password;
    private long mob_number;
    
    @OneToMany(mappedBy = "canteenManager")
    private List<FoodInventory> foodInventory;
    
	public CanteenManager() {
		super();
	}

	public CanteenManager(int id, String first_name,String last_name, String role, String email, String address, long aadhar_number,
			String password, String confirm_password, long mob_number) {
		super();
		this.id = id;
		this.First_name = first_name;
		this.Last_name=last_name;
		this.role = role;
		this.email = email;
		this.address = address;
		this.aadhar_number = aadhar_number;
		this.password = password;
		this.confirm_password = confirm_password;
		this.mob_number = mob_number;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getAadhar_number() {
		return aadhar_number;
	}

	public void setAadhar_number(long aadhar_number) {
		this.aadhar_number = aadhar_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public long getMob_number() {
		return mob_number;
	}

	public void setMob_number(long mob_number) {
		this.mob_number = mob_number;
	}



}
