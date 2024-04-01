package com.example.CanteenManagementSystem.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.repository.CanteenManagerRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CanteenManagerService {
	@Autowired
	CanteenManagerRepo canteenRepo;

	public List<CanteenManager> getAllManagers() throws Exception {
		List<CanteenManager> managers = canteenRepo.findAll();
		if (!managers.isEmpty()) {
			// Configure ObjectMapper to use custom serializer
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addSerializer(Blob.class, new BlobSerializer());
			objectMapper.registerModule(module);

			// Convert CanteenManager objects to JSON with image data included
			try {
				String jsonManagers = objectMapper.writeValueAsString(managers);
				System.out.println(jsonManagers); // For debugging purposes
				return managers;
			} catch (JsonProcessingException e) {
				// Handle JSON serialization error
				e.printStackTrace(); // Handle or log the exception
				throw new Exception("Error processing JSON");
			}
		} else {
			throw new EntityNotFoundException("No records found");
		}
	}

	public CanteenManager addManager(MultipartFile image, String first_name, String last_name, String email,
			String address, long aadhar_number, String password, long mob_number)
			throws IOException, SerialException, SQLException {
		CanteenManager manager = new CanteenManager();
		manager.setFirst_name(first_name);
		manager.setLast_name(last_name);
		manager.setEmail(email);
		manager.setAddress(address);
		manager.setAadhar_number(aadhar_number);
		manager.setPassword(password);
		manager.setMob_number(mob_number);

		if (image != null && !image.isEmpty()) {
			byte[] photoBytes = image.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			manager.setImage(photoBlob);
		}

		return canteenRepo.save(manager);
	}

	public void deleteCanteenManager(int id) {
		canteenRepo.deleteById(id);

	}

	public void updateManagerFields(CanteenManager manager, Map<String, Object> fields)
			throws IllegalAccessException, NoSuchFieldException, SerialException, SQLException, IOException {
		for (Map.Entry<String, Object> entry : fields.entrySet()) {
			String fieldName = entry.getKey();
			Object value = entry.getValue();

			try {
				Field field = manager.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);

				if ("file".equals(fieldName)) { // Check if the field is 'file'
					MultipartFile file = (MultipartFile) value;
					if (!file.isEmpty()) {
						byte[] photoBytes = file.getBytes();
						Blob photoBlob = new SerialBlob(photoBytes);
						field.set(manager, photoBlob);
					} else {
						field.set(manager, null);
					}
				} else if (field.getType() == LocalDate.class && value instanceof String) {
					// Convert String to LocalDate
					LocalDate newValue = LocalDate.parse((String) value, DateTimeFormatter.ISO_DATE);
					field.set(manager, newValue);
				} else if (field.getType() == long.class && value instanceof String) {
					// Convert String to long
					long newValue = Long.parseLong((String) value);
					field.set(manager, newValue);
				} else {
					field.set(manager, value);
				}
			} catch (NoSuchFieldException e) {
				// Handle the case where the field doesn't exist
				System.out.println("Field '" + fieldName + "' does not exist in CanteenManager class.");
			}
		}
	}

	public CanteenManager updateManager(CanteenManager manager) {
		return canteenRepo.save(manager);
	}

	public CanteenManager getManagerById(int id) {
		Optional<CanteenManager> s = canteenRepo.findById(id);
		if (s.isPresent()) {
			CanteenManager c = s.get();
			return c;

		} else {
			throw new NotFoundException("no Records found");

		}
	}

	/*
	 * public List<CanteenManager> getAllCanteenManagers() { List<CanteenManager>
	 * canteenManagers = canteenRepo.findAll(); if (!canteenManagers.isEmpty()) {
	 * return canteenManagers; } else { throw new
	 * NotFoundException("No records found"); } }
	 */
	public List<CanteenManager> getAllCanteenManagers() throws Exception {
		List<CanteenManager> managers = canteenRepo.findAll();
		if (managers.isEmpty()) {
			throw new EntityNotFoundException("No records found");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Blob.class, new BlobSerializer());
		objectMapper.registerModule(module);

		try {
			// Convert FoodInventory objects to JSON with image data included
			String jsonFoods = objectMapper.writeValueAsString(managers);
			System.out.println(jsonFoods); // For debugging purposes
			return managers;
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // Handle or log the exception
			throw new Exception("Error processing JSON");
		}
	}

	public CanteenManager getCanteenManagerById(int id) {
		Optional<CanteenManager> canteenManagerOptional = canteenRepo.findById(id);
		if (canteenManagerOptional.isPresent()) {
			return canteenManagerOptional.get();
		} else {
			throw new NotFoundException("Canteen manager with ID " + id + " not found");
		}
	}

}
