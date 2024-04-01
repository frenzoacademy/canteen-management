package com.example.CanteenManagementSystem.Controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.service.FoodInventoryService;

@ControllerAdvice
@RestController
@RequestMapping("/foodInventory")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodInventoryController {
	
	/*@Autowired
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

	
	
	
	 @PostMapping
	    public ResponseEntity<FoodInventory> saveFoodItem(@RequestParam("photo") MultipartFile photo,
	                               @RequestParam("name") String name,
	                               @RequestParam("amount") int amount,
	                               @RequestParam("breakfast") boolean breakfast,
	                               @RequestParam("lunch") boolean lunch,
	                               @RequestParam("eveningfood") boolean eveningfood,
	                               @RequestParam("dinner") boolean dinner,
	                               @RequestParam("alltime") boolean alltime,
	                               @RequestParam("quantity") int quantity) throws SerialException, IOException, SQLException {
		 FoodInventory food = foodService.addFoodInventory(photo, name, amount, breakfast, lunch, eveningfood,
				 dinner, alltime, quantity);
			return new ResponseEntity<FoodInventory>(food, HttpStatus.CREATED);
		
	 }
	
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable int id) {
		foodService.deletefood(id);
		return "delete record";
	}
	@PatchMapping("/{id}")
	public FoodInventory updateFoodByField(@PathVariable int id,@RequestBody Map<String,Object> fields) {
		return foodService.updateFoodByField(id,fields);
	}*/
	
	
	 @Autowired
	    private FoodInventoryService foodInventoryService;

	    @GetMapping("/{id}")
	    public ResponseEntity<FoodInventory> getFoodById(@PathVariable int id) {
	        FoodInventory food = foodInventoryService.getFoodById(id);
	        return ResponseEntity.ok(food);
	    }

	    @GetMapping
	    public ResponseEntity<List<FoodInventory>> getAllFoods() {
	        List<FoodInventory> foods = foodInventoryService.getAllFoods();
	        return ResponseEntity.ok(foods);
	    }

	    @PostMapping
	    public ResponseEntity<FoodInventory> addFood(@RequestParam("file") MultipartFile file,
	                                                  @RequestParam("name") String name,
	                                                  @RequestParam("amount") int amount,
	                                                  @RequestParam("isAvailability") boolean isAvailability,
	                                                  @RequestParam("breakfast") boolean breakfast,
	                                                  @RequestParam("lunch") boolean lunch,
	                                                  @RequestParam("eveningfood") boolean eveningfood,
	                                                  @RequestParam("dinner") boolean dinner,
	                                                  @RequestParam("alltime") boolean alltime,
	                                                  @RequestParam("quantity") int quantity) throws SerialException {
	        try {
	            Blob photoBlob = new SerialBlob(file.getBytes());
	            FoodInventory food = new FoodInventory(quantity, name, amount, isAvailability, breakfast, lunch, eveningfood, dinner, alltime, quantity, photoBlob);
	            foodInventoryService.addFood(food, file);
	            return ResponseEntity.status(HttpStatus.CREATED).body(food);
	        } catch (IOException | SQLException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteFood(@PathVariable int id) {
	        foodInventoryService.deleteFood(id);
	        return ResponseEntity.ok("Food with ID " + id + " has been deleted.");
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<FoodInventory> updateFood(@PathVariable int id,
	                                                     @RequestParam(value = "file", required = false) MultipartFile file,
	                                                     @RequestParam("name") String name,
	                                                     @RequestParam("amount") int amount,
	                                                     @RequestParam("isAvailability") boolean isAvailability,
	                                                     @RequestParam("breakfast") boolean breakfast,
	                                                     @RequestParam("lunch") boolean lunch,
	                                                     @RequestParam("eveningfood") boolean eveningfood,
	                                                     @RequestParam("dinner") boolean dinner,
	                                                     @RequestParam("alltime") boolean alltime,
	                                                     @RequestParam("quantity") int quantity) throws SerialException {
	        try {
	            Optional<FoodInventory> optionalFood = Optional.ofNullable(foodInventoryService.getFoodById(id));
	            if (optionalFood.isPresent()) {
	                FoodInventory food = optionalFood.get();
	                if (file != null && !file.isEmpty()) {
	                    Blob photoBlob = new SerialBlob(file.getBytes());
	                    food.setPhoto(photoBlob);
	                }
	                food.setName(name);
	                food.setAmount(amount);
	                food.setAvailability(isAvailability);
	                food.setBreakfast(breakfast);
	                food.setLunch(lunch);
	                food.setEveningfood(eveningfood);
	                food.setDinner(dinner);
	                food.setAlltime(alltime);
	                food.setQuantity(quantity);
	                foodInventoryService.updateFood(food, file);
	                return ResponseEntity.ok(food);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (IOException | SQLException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
}
