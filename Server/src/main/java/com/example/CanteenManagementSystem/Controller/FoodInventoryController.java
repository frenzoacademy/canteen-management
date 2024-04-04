package com.example.CanteenManagementSystem.Controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.codec.binary.Base64;
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
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.model.PurchaseOrder;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.model.StudentFormResponse;
import com.example.CanteenManagementSystem.repository.FoodInventoryRepo;
import com.example.CanteenManagementSystem.repository.PurchaseOrderRepo;
import com.example.CanteenManagementSystem.service.FoodInventoryService;

@ControllerAdvice
@RestController
@RequestMapping("/foodInventory")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodInventoryController {

	@Autowired
	private FoodInventoryService foodInventoryService;

	@GetMapping
	public ResponseEntity<List<FoodInventory>> getAllFoods() throws Exception {
		List<FoodInventory> foods = foodInventoryService.getAllFoods();
		System.out.println(foods.size() + "   size");
		for (FoodInventory food : foods) {
			Blob photoBytes = foodInventoryService.getFoodPhotoByFoodId(food.getFood_id());

			if (photoBytes != null && photoBytes.length() > 0) {
				String base64Photo = Base64.encodeBase64String(photoBytes.getBytes(1, (int) photoBytes.length()));
				FoodInventory foodData = getFoodFormResponse(food);
				food.setPhoto(photoBytes);
				food.setPhotoBase64(base64Photo);
			}
		}
		return ResponseEntity.ok(foods);
	}

	private FoodInventory getFoodFormResponse(FoodInventory food) {
		byte[] photoBytes = null;
		Blob photoBlob = food.getPhoto();
		if (photoBlob != null) {
			try {
				photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
			} catch (SQLException e) {
				throw new NotFoundException("Error retrieving photo");
			}
		}

		return new FoodInventory(food.getFood_id(), food.getName(), food.getAmount(), food.getPhoto(),
				food.getQuantity(), food.isAlltime(), food.isAvailability(), food.isBreakfast(), food.isDinner(),
				food.isEveningfood(), food.isLunch());

	}

	@GetMapping("/{id}")
	public ResponseEntity<FoodInventory> getFoodById(@PathVariable int id) throws Exception {
		Optional<FoodInventory> foodOptional = Optional.ofNullable(foodInventoryService.getFoodById(id));
		if (foodOptional.isPresent()) {
			FoodInventory food = foodOptional.get();
			Blob photoBytes = foodInventoryService.getFoodPhotoByFoodId(food.getFood_id());
			if (photoBytes != null && photoBytes.length() > 0) {
				String base64Photo = Base64.encodeBase64String(photoBytes.getBytes(1, (int) photoBytes.length()));
				FoodInventory foodData = getFoodFormResponse(food);
				food.setPhoto(photoBytes);
				food.setPhotoBase64(base64Photo);
			}
			return ResponseEntity.ok(food);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<FoodInventory> addFood(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("amount") int amount,
			@RequestParam("isAvailability") boolean isAvailability, @RequestParam("breakfast") boolean breakfast,
			@RequestParam("lunch") boolean lunch, @RequestParam("eveningfood") boolean eveningfood,
			@RequestParam("dinner") boolean dinner, @RequestParam("alltime") boolean alltime,
			@RequestParam("quantity") int quantity) throws SerialException {
		try {
			Blob photoBlob = new SerialBlob(file.getBytes());
			FoodInventory food = new FoodInventory(quantity, name, amount, isAvailability, breakfast, lunch,
					eveningfood, dinner, alltime, quantity, photoBlob);
			foodInventoryService.addFood(food, file);
			return ResponseEntity.status(HttpStatus.CREATED).body(food);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFood(@PathVariable int id) {
		foodInventoryService.deleteFood(id);
		return ResponseEntity.ok("Food with ID " + id + " has been deleted.");
	}*/
	
	
	@Autowired
	FoodInventoryRepo foodInventoryRepository;
	
	@Autowired
	PurchaseOrderRepo purchaseOrderRepository;
	
	@DeleteMapping("/{id}")
	public void deleteFoodInventory(@PathVariable int id) {
	    Optional<FoodInventory> foodInventory = foodInventoryRepository.findById(id);
	    if (foodInventory.isPresent()) {
	    	FoodInventory food=foodInventory.get();
	        List<PurchaseOrder> purchaseOrders = food.getPurchaseOrders();
	        for (PurchaseOrder purchaseOrder : purchaseOrders) {
	            purchaseOrderRepository.delete(purchaseOrder);
	        }
	        
	        foodInventoryRepository.delete(food);
	    }
	}


	@Autowired
	FoodInventoryRepo foodInventoryRepo;

	@PutMapping("/{id}")
	public ResponseEntity<?> updateFood(@PathVariable int id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "amount", required = false) Integer amount,
			@RequestParam(value = "isAvailability", required = false) Boolean isAvailability,
			@RequestParam(value = "breakfast", required = false) Boolean breakfast,
			@RequestParam(value = "lunch", required = false) Boolean lunch,
			@RequestParam(value = "eveningfood", required = false) Boolean eveningfood,
			@RequestParam(value = "dinner", required = false) Boolean dinner,
			@RequestParam(value = "alltime", required = false) Boolean alltime,
			@RequestParam(value = "quantity", required = false) Integer quantity) throws SQLException, IOException {
		try {
			Optional<FoodInventory> optionalFood = Optional.ofNullable(foodInventoryService.getFoodById(id));
			if (optionalFood.isPresent()) {
				FoodInventory food = optionalFood.get();

				if (name != null) {
					food.setName(name);
				}
				if (amount != null) {
					food.setAmount(amount);
				}
				if (isAvailability != null) {
					food.setAvailability(isAvailability);
				}
				if (breakfast != null) {
					food.setBreakfast(breakfast);
				}
				if (lunch != null) {
					food.setLunch(lunch);
				}
				if (eveningfood != null) {
					food.setEveningfood(eveningfood);
				}
				if (dinner != null) {
					food.setDinner(dinner);
				}
				if (alltime != null) {
					food.setAlltime(alltime);
				}
				if (quantity != null) {
					food.setQuantity(quantity);
				}

				foodInventoryRepo.save(food);
				return ResponseEntity.ok(food);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Error occurred while processing the request.");
		}
	}

}
