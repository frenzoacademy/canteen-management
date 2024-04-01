package com.example.CanteenManagementSystem.model;

import java.sql.Blob;
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
    private String email;
    private String address;
    private long aadhar_number;
    private String password;
    private long mob_number;
    private Blob image;
    
    @OneToMany(mappedBy = "canteenManager")
    private List<FoodInventory> foodInventory;
    
	public CanteenManager() {
		super();
	}

	

	public CanteenManager(int id, String first_name, String last_name,  String email, String address,
			long aadhar_number, String password,  long mob_number, Blob image,
			List<FoodInventory> foodInventory) {
		super();
		this.id = id;
		First_name = first_name;
		Last_name = last_name;
		this.email = email;
		this.address = address;
		this.aadhar_number = aadhar_number;
		this.password = password;
		this.mob_number = mob_number;
		this.image = image;
		this.foodInventory = foodInventory;
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

	public long getMob_number() {
		return mob_number;
	}

	public void setMob_number(long mob_number) {
		this.mob_number = mob_number;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}



}
