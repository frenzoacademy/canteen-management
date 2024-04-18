package com.example.CanteenManagementSystem.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.model.PurchaseOrder;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.repository.FoodInventoryRepo;
import com.example.CanteenManagementSystem.repository.PurchaseOrderRepo;
import com.example.CanteenManagementSystem.repository.StudentFormRepo;
import com.example.CanteenManagementSystem.service.FoodInventoryService;
import com.example.CanteenManagementSystem.service.PurchaseOrderService;

@ControllerAdvice
@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseOrderController {
	@Autowired
	PurchaseOrderService purchaseService;

	@GetMapping
	public ResponseEntity<List<PurchaseOrder>> getPurchaseOrder() {
		List<PurchaseOrder> purchaseorder = purchaseService.getOrder();
		return new ResponseEntity<List<PurchaseOrder>>(purchaseorder, HttpStatus.OK);
	}

	@Autowired
	StudentFormRepo studentRepo;

	
	@PostMapping
    @Transactional
    public ResponseEntity<String> addPurchaseOrder(@RequestBody PurchaseOrder request) {
        try {
            Optional<StudentForm> studentOptional = studentRepo.findById(request.getStudentForm().getStudent_id());
            if (studentOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
            StudentForm student = studentOptional.get();

            int totalAmount = 0;
            for (FoodInventory foodItem : request.getFoodItems()) {
                Optional<FoodInventory> foodOptional = foodInventoryRepository.findByFoodId(foodItem.getFood_id());
                if (foodOptional.isPresent()) {
                    totalAmount += foodOptional.get().getAmount() * foodItem.getQuantity();
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item not found");
                }
            }

            if (student.getWallet() < totalAmount) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
            }

            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setStudentForm(student);
            purchaseOrder.setTotalAmount(totalAmount);
            purchaseOrder.setStatus(request.getStatus());
            LocalDateTime date_time;

            purchaseOrder.setDate_time(LocalDateTime.now().toLocalDate());

            
            List<FoodInventory> foodItems = new ArrayList<>();
            for (FoodInventory foodItem : request.getFoodItems()) {
                Optional<FoodInventory> foodOptional = foodInventoryRepository.findByFoodId(foodItem.getFood_id());
                if (foodOptional.isPresent()) {
                    FoodInventory foodInventory = foodOptional.get();
                    if (foodInventory.getQuantity() < foodItem.getQuantity()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient quantity for food item");
                    }
                    student.setWallet(student.getWallet() - totalAmount);
                    foodInventory.setQuantity(foodInventory.getQuantity() - foodItem.getQuantity());
                    foodItems.add(foodInventory);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item not found");
                }
            }
            purchaseOrder.setFoodItems(foodItems);

            purchaseOrderRepository.save(purchaseOrder);

            studentRepo.save(student);

            return ResponseEntity.status(HttpStatus.CREATED).body("Purchase order created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

	@Autowired
	FoodInventoryService foodInventoryService;

	@Autowired
	FoodInventoryRepo foodInventoryRepository;
	@Autowired
	PurchaseOrderRepo purchaseOrderRepository;

	@PutMapping("/{id}")
	public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable int id,
			@RequestBody PurchaseOrder purchaseOrder) {
		PurchaseOrder updatedPurchaseOrder = purchaseService.updatePurchaseOrder(id, purchaseOrder);
		return ResponseEntity.ok(updatedPurchaseOrder);
	}

	@DeleteMapping("/{id}")
	public String deleteOrders(@PathVariable int id) {
		purchaseService.deleteOrder(id);
		return "delete record";
	}

	@GetMapping("/{id}")
	public ResponseEntity<PurchaseOrder> getStudentById(@PathVariable int id) {
		PurchaseOrder p = purchaseService.getStudentById(id);
		return new ResponseEntity<PurchaseOrder>(p, HttpStatus.OK);
	}

}
