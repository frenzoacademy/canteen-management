package com.example.CanteenManagementSystem.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.repository.FoodInventoryRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Service
public class FoodInventoryService {

	@Autowired
	private FoodInventoryRepo foodInventoryRepository;

	public List<FoodInventory> getAllFoods() throws Exception {
		List<FoodInventory> foods = foodInventoryRepository.findAll();
		if (foods.size() > 0) {
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addSerializer(Blob.class, new BlobSerializer());
			objectMapper.registerModule(module);

			try {
				String jsonFoods = objectMapper.writeValueAsString(foods);
				return foods;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new Exception("Error processing JSON");
			}
		} else {
			throw new NotFoundException("No records found");
		}
	}

	public Blob getFoodPhotoByFoodId(int id) throws SQLException {
		Optional<FoodInventory> food = foodInventoryRepository.findById(id);
		if (food.isEmpty()) {
			throw new NotFoundException("Sorry, Student not found!");
		}
		Blob photoBlob = food.get().getPhoto();
		if (photoBlob != null) {
			return photoBlob;
		}
		return null;
	}

	public FoodInventory getFoodById(int id) {
		Optional<FoodInventory> food = foodInventoryRepository.findById(id);
		if (food.isPresent()) {
			return food.get();
		} else {
			throw new NotFoundException("Food not found with ID: " + id);
		}
	}

	public FoodInventory addFood(FoodInventory food, MultipartFile file) throws IOException, SQLException {
		if (!file.isEmpty()) {
			byte[] photoBytes = file.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			food.setPhoto(photoBlob);
		}
		return foodInventoryRepository.save(food);
	}

	public void deleteFood(int id) {
		if (foodInventoryRepository.existsById(id)) {
			foodInventoryRepository.deleteById(id);
		} else {
			throw new NotFoundException("Food not found with ID: " + id);
		}
	}

	public void updateFood(FoodInventory food, Object object) {
		foodInventoryRepository.save(food);
	}
}
