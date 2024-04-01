package com.example.CanteenManagementSystem.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.repository.FoodInventoryRepo;

@Service
public class FoodInventoryService {

	/*@Autowired
	FoodInventoryRepo foodRepo;
	public List<FoodInventory> getFood(){
		List<FoodInventory> f=foodRepo.findAll();
		if(f.size()>0) {
			return f;
		}else {
			throw new NotFoundException("no records found");
		}
		
	}
//	public FoodInventory addFood(FoodInventory food) {
//		FoodInventory f=foodRepo.save(food);
//		return f;
//	}
	
//	public FoodInventory updateFood(FoodInventory food) {
//		Optional<FoodInventory> f=foodRepo.findById(food.getFood_id());
//		if(f.isPresent()) {
//			FoodInventory fi=f.get();
//			if(food.getName()!=null) {
//				fi.setName(food.getName());
//			}
//			if(food.getAmount()!=0) {
//				fi.setAmount(food.getAmount());
//			}
//			if(food.isAvailability()!=false) {
//				fi.setAvailability(food.isAvailability());
//			}
//			if(food.getQuantity()!=0) {
//				fi.setQuantity(food.getQuantity());
//			}
//			foodRepo.save(fi);
//			return fi;
//		}
//		else {
//			food=foodRepo.save(food);
//		return food;
//		
//	}
//	}
	public void deletefood(int id) {
		foodRepo.deleteById(id);
	}
	
	public FoodInventory updateFoodByField(int id, Map<String, Object> fields) {
		Optional<FoodInventory> f=foodRepo.findById(id);
		if(f.isPresent()) {
		fields.forEach((key,value)->{
			Field field=ReflectionUtils.findField(FoodInventory.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, f.get(), value);
		});
		return foodRepo.save(f.get());
	}
		return null;
	}
	public FoodInventory getfoodByid(int food_id) {
		Optional<FoodInventory>s=foodRepo.findById(food_id);
		if(s.isPresent()) {
			FoodInventory st=s.get();
			return st;
		}else {
		throw new NotFoundException("no records");
	}
	}
	public FoodInventory addFoodInventory(MultipartFile file, String name, int amount, boolean breakfast,
                                          boolean lunch, boolean eveningfood, boolean dinner, boolean alltime,
                                          int quantity) throws IOException, SerialException, SQLException {
        FoodInventory food = new FoodInventory();
        food.setName(name);
        food.setAmount(amount);
        food.setBreakfast(breakfast);
        food.setLunch(lunch);
        food.setEveningfood(eveningfood);
        food.setDinner(dinner);
        food.setAlltime(alltime);
        food.setQuantity(quantity);

        if (!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            food.setPhoto(photoBlob);
        }

        return foodRepo.save(food);
    }*/
	  @Autowired
	    private FoodInventoryRepo foodInventoryRepository;

	    public List<FoodInventory> getAllFoods() {
	        List<FoodInventory> foods = foodInventoryRepository.findAll();
	        if (foods.size() > 0) {
	            return foods;
	        } else {
	            throw new NotFoundException("No records found");
	        }
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

	    public FoodInventory updateFood(FoodInventory food, MultipartFile file) throws IOException, SQLException {
	        if (!file.isEmpty()) {
	            byte[] photoBytes = file.getBytes();
	            Blob photoBlob = new SerialBlob(photoBytes);
	            food.setPhoto(photoBlob);
	        }
	        return foodInventoryRepository.save(food);
	    }
}
