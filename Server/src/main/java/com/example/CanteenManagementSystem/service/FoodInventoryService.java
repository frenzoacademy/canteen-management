package com.example.CanteenManagementSystem.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.model.PurchaseOrder;
import com.example.CanteenManagementSystem.repository.FoodInventoryRepo;

@Service
public class FoodInventoryService {

	@Autowired
	FoodInventoryRepo foodRepo;
	public List<FoodInventory> getFood(){
		List<FoodInventory> f=foodRepo.findAll();
		if(f.size()>0) {
			return f;
		}else {
			throw new NotFoundException("no records found");
		}
		
	}
	public FoodInventory addFood(FoodInventory food) {
		FoodInventory f=foodRepo.save(food);
		return f;
	}
	
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
}
