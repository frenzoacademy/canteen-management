package com.example.CanteenManagementSystem.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.service.FoodInventoryService;

@ControllerAdvice
@RestController
@RequestMapping("/foodInventory")
public class FoodInventoryController {
	@Autowired
	FoodInventoryService foodService;
	@GetMapping
	public ResponseEntity<List<FoodInventory>>getFoodDetails(){
		List<FoodInventory> foodInventory=foodService.getFood();
		return new ResponseEntity<List<FoodInventory>>(foodInventory,HttpStatus.OK);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<FoodInventory>getFoodById(@PathVariable int id){
		FoodInventory f=foodService.getfoodByid(id);
		return new ResponseEntity<FoodInventory>(f,HttpStatus.OK);	
	}
//	@PutMapping
//	 	public ResponseEntity<FoodInventory>updatefood(@RequestBody FoodInventory food){
//		FoodInventory c=foodService.updateFood(food);
//		return new ResponseEntity<FoodInventory>(c,HttpStatus.OK);
//	}
	@PostMapping 
	public ResponseEntity<FoodInventory> addFoods(@RequestBody FoodInventory food){
		FoodInventory f=foodService.addFood(food);
		return new ResponseEntity<FoodInventory>(f,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable int id) {
		foodService.deletefood(id);
		return "delete record";
	}
	@PatchMapping("/{id}")
	public FoodInventory updateFoodByField(@PathVariable int id,@RequestBody Map<String,Object> fields) {
		return foodService.updateFoodByField(id,fields);
	}
}
